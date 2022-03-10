package controller;

import model.RoomTracker;
import model.objects.Player;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import json_data.ConnectionSetup;
import json_data.ErrorRes;
import json_data.RecievingCmd;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * A class that manages the connection to the client and the player data associated with it
 * @see Socket
 * @see Player
 *
 * @author Alen
 * @version 2022-03-07
 */
public class GameConnection implements Runnable, Observer, Closeable {
    private final Socket conn;
    private final BufferedReader input;
    private final PrintWriter output;
    private final Gson gson;
    private Player player;
    private Room room;

    /**
     * Sets up the connection and it's streams while recieving the player name and the room code from client.
     * Afterwards the connection associates itself with a room.
     * @param s the connection established
     * @throws IOException when the connection disconnects
     */
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

    /**
     * The algorithm run in its own thread. It listens for a command and calls the appropriate method.
     * @see GameConnection#handleCmd(RecievingCmd)
     * @see Runnable#run()
     */
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

    /**
     * Reads a json string that is closed.
     * @return A json string from the client
     * @throws IOException when the connection closes
     */
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

    /**
     * Whenever the game updates send a json string to the client
     * @param o the room
     * @param arg the json string
     */
    @Override
    public void update(Observable o, Object arg) {
        //System.out.println(arg);
        output.println(arg.toString());
    }

    /**
     * Get the {@link Player} that is associated with the connection
     * @return get the associated player data
     */
    public Player getPlayer(){ return player; }

    /**
     * Closes the socket associated with this connection
     * @throws IOException when the socket fails to close or is already closed
     * @see Closeable#close()
     */
    @Override
    public void close() throws IOException {
        conn.close();
    }

    /**
     * Calls the appropriated method at the room according to the parsed json
     * @param cmd the parsed command
     */
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

    /**
     * sends an error to the client
     * @param type the error type
     * @param msg the eeror message
     */
    public void sendError(String type, String msg){
            output.println(gson.toJson(new ErrorRes(type, msg)));
    }

    /**
     * Returns a boolean based on if the {@link Socket} is closed
     * @return a boolean if the connection is closed
     */
    public boolean isClosed() { return conn.isClosed(); }
}