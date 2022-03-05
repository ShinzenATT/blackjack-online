package Controller;

import Model.RoomTracker;
import Model.objects.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import json_data.ConnectionSetup;
import json_data.ErrorRes;
import json_data.RecievingCmd;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class GameConnection implements Runnable, Observer, Closeable {
    private final Socket conn;
    private final BufferedReader input;
    private final PrintWriter output;
    private final Gson gson;
    private Player player;
    private Room room;

    public GameConnection(Socket s) throws IOException {
        conn = s;
        input = new BufferedReader( new InputStreamReader(conn.getInputStream()));
        output = new PrintWriter(conn.getOutputStream(), true);
        gson = new Gson();

        ConnectionSetup cs;
        do {
            try {
                cs = gson.fromJson(ReadFile(), ConnectionSetup.class);
                if(cs == null){
                    return;
                }
                if (cs.player_name == null) {
                    throw new IllegalArgumentException("Player name was missing");
                }
            } catch (JsonSyntaxException e) {
                output.println(gson.toJson(new ErrorRes("Invalid syntax", e.getMessage())));
                cs = null;
            } catch (IllegalArgumentException e) {
                output.println(gson.toJson(new ErrorRes("Missing field", e.getMessage())));
                conn.close();
                cs = null;
            }

            if(cs != null) {
                player = new Player(cs.player_name, 1000);
                if (cs.room_code != null) {
                    try {
                        room = RoomTracker.joinRoom(cs.room_code, this);
                    } catch (IllegalArgumentException e){
                        cs = null;
                    }
                    System.out.println(this.toString() + " joined room " + room);
                } else {
                    room = RoomTracker.createRoom(this);
                    System.out.println(this.toString() + " created room " + room);
                }
            }
        }while (cs == null);
    }

    @Override
    public void run() {
        try (conn) {
            System.out.println("Opened connection " + this + " at  thread " + Thread.currentThread().getName());
            while (!conn.isClosed()){
                try {
                    handleCmd(gson.fromJson(ReadFile(), RecievingCmd.class));
                } catch (JsonSyntaxException e){
                    output.println(gson.toJson(new ErrorRes("Illegal JSON syntax", e.getMessage() )));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println("Closed connection at " + Thread.currentThread().getName());
    }

    private String ReadFile() throws IOException {
        StringBuilder str = new StringBuilder();
        String line;
        int nests = 0;
        do {
            line = input.readLine();

            if(line == null){
                break;
            }

            str.append(line).append('\n');
            if(line.contains("{")){
                nests++;
            } if(line.contains("}")){
                nests--;
            }
        }while (nests > 0);

        //System.out.println(str);

        return str.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println(arg);
        output.println(arg.toString());
    }

    public Player getPlayer(){ return player; }

    @Override
    public void close() throws IOException {
        conn.close();
    }

    private void handleCmd(RecievingCmd cmd){
        if(cmd == null){
            try {
                close();
            }catch (IOException e){
                e.printStackTrace();
            }
            return;
        }

        switch (cmd.command){
            case "start" -> room.startGame(this);
            case "hit" -> room.hit(this);
            case "stand" -> room.stand(this);
            case "split" -> room.split(this);
            case "bet" -> room.bet(cmd.value, this);
            case "double down" -> room.doubleDown(this);
            default -> output.println(gson.toJson(new ErrorRes("unknown cmd", "The command " + cmd.command + " is not a available")));
        }
    }

    public void sendError(String type, String msg){
            output.println(gson.toJson(new ErrorRes(type, msg)));
    }

    public boolean isClosed() { return conn.isClosed(); }
}