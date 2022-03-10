package controller;

import model.RoomTracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CompletableFuture;

/**
 * A class for setting up a server socket and starting them in their own threads
 * @see SocketManager#start()
 *
 * @author Alen
 * @version 2022-03-07
 */
public class SocketManager {

    /**
     * Starts the server socket at port 8080 and starts accepting connections. When a connection is accepted a {@link GameConnection} 
     * object is created with its properties and is later run in its own thread. Afterwards it starts accepting new connections.
     * @throws IOException when the server socket cannot be connected like the port being unusable or no network interface being available
     */
    public static void start() throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        Runtime.getRuntime().addShutdownHook(new GracefulShutdown(socket));
        System.out.println("Opened tcp socket at port 8080");

        while (!socket.isClosed()){
            try {
                GameConnection gc = new GameConnection(socket.accept());
                CompletableFuture.runAsync(gc)
                        // print errors in its thread
                        .exceptionallyAsync(e -> {
                            e.printStackTrace();
                            return null;
                            // Log the connection closing
                        }).whenCompleteAsync((v, t) -> System.out.println("Closed connection " + gc));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * A shutdown routine that is runs in its own thread when the application closes. It closes all the connections and the server socket.
     * @see java.lang.Thread 
     * @see Runtime#addShutdownHook(Thread) 
     */
    private static class GracefulShutdown extends Thread{
        /**
         * The server socket pointer that is to be shutdown in the routine
         */
        ServerSocket socket;

        /**
         * Prepares the shutdown routine by initializing a thread and saves the pointer to the server socker
         * @param s the pointer to the server socket to be closed during the routine
         * @see Thread
         */
        GracefulShutdown(ServerSocket s){
            super();
            socket = s;
        }

        /**
         * The routine that is run when the shutdown hook has initiated. It closes all the sockets and the server socket.
         * @see Runnable
         * @see ServerSocket#close() 
         * @see RoomTracker#close() 
         * @see Runtime#addShutdownHook(Thread) 
         */
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
