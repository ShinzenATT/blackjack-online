package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import json_data.ErrorRes;
import json_data.RecievingCmd;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class GameConnection implements Runnable, Observer {
    private final Socket conn;
    private final BufferedReader input;
    private final PrintWriter output;
    private final Gson gson;

    public GameConnection(Socket s) throws IOException {
        conn = s;
        input = new BufferedReader( new InputStreamReader(conn.getInputStream()));
        output = new PrintWriter(conn.getOutputStream(), true);
        gson = new Gson();
    }

    @Override
    public void run() {
        try (conn) {
            System.out.println("Opened connection at " + this.toString() + " in thread " + Thread.currentThread().getName());
            while (conn.isConnected()){
                try {
                    System.out.println(gson.fromJson(ReadFile(), RecievingCmd.class).toString());
                } catch (JsonSyntaxException e){
                    output.println(gson.toJson(new ErrorRes("Illegal JSON syntax", e.getMessage() )));
                }
                output.println(gson.toJson(new RecievingCmd("Ping " + Thread.currentThread().getName(), 100)));
            }
            System.out.println("Closed connection at " + Thread.currentThread().getName());
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
            str.append(line).append('\n');
            if(line.contains("{")){
                nests++;
            } if(line.contains("}")){
                nests--;
            }
        }while (nests > 0);

        return str.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        output.println(arg.toString());
    }
}