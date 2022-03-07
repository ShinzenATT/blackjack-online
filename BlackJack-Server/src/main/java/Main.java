import Controller.SocketManager;

import java.io.IOException;

/**
 * Our main method starts the application by adding
 * a new gametracker, gamewindow and controller
 * @author Alen
 * @version 2022-03-07.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        SocketManager.Start();
    }
}
