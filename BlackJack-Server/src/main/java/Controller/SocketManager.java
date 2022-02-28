package Controller;

import Controller.GameConnection;
import Model.RoomTracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CompletableFuture;

public class SocketManager {

    public static void Start() throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        Runtime.getRuntime().addShutdownHook(new GracefulShutdown(socket));
        System.out.println("Opened tcp socket at port 8080");

        while (!socket.isClosed()){
            try {
                GameConnection gc = new GameConnection(socket.accept());
                CompletableFuture.runAsync(gc)
                        .exceptionallyAsync(e -> {
                            e.printStackTrace();
                            return null;
                        }).whenCompleteAsync((v, t) -> System.out.println("Closed connection " + gc));
            } catch (IOException e){
                System.out.println("There was a problem with the connection");
                e.printStackTrace();
            }
        }
    }

    private static class GracefulShutdown extends Thread{
        ServerSocket socket;

        GracefulShutdown(ServerSocket s){
            super();
            socket = s;
        }

        @Override
        public void run() {
            System.out.println("Starting graceful shutdown...");
            super.run();
            RoomTracker.close();
            try {
                socket.close();
                System.out.println("Socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
