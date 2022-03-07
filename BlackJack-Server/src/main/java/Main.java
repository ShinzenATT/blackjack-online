import Controller.SocketManager;

import java.io.IOException;

/**
 * Our main method. Start the application by adding
 * a new gametracker, gamewindow and controller
 * @author Alen
 * @version 2022-03-07.
 * 
 * @param args The command line arguments.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        SocketManager.Start();
    }
}
