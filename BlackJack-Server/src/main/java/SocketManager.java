import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.CompletableFuture;

public class SocketManager {

    public static void Start() throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        Runtime.getRuntime().addShutdownHook(new GracefulShutdown(socket));

        while (!socket.isClosed()){
            try {
                GameConnection gc = new GameConnection(socket.accept());
                CompletableFuture.runAsync(gc)
                        .thenRun(() -> System.out.println("Closed connection " + gc));
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
            try {
                socket.close();
                System.out.println("Socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
