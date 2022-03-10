import controller.SocketManager;

import java.io.IOException;

/**
 * The main class that initializes the server
 * @see Main#main(String[])
 *
 * @author Alen
 * @version 2022-03-07.
 */
public class Main {

    /**
     * Our main method starts the application by starting the {@link java.net.ServerSocket ServerSocket}
     * @param args the commandline arguments on application launch
     * @throws IOException when the serversocket fails to initialize in some cases like the server port already being in use
     * @see SocketManager#start()
     */
    public static void main(String[] args) throws IOException {
        SocketManager.start();
    }
}
