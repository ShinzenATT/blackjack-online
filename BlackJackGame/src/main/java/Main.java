import controller.Controller;
import model.GameTracker;
import view.*;

import java.io.IOException;
/**
 * The main class. Runs the application.
 * @author Alen
 * @version 2022-03-07
 */
public class Main {

/**
 * Our main method. Start the application by adding
 * a new gametracker, gamewindow and controller.
 * 
 * @param args The command line arguments.
 */
public static void main(String[] args) {

  try {
    GameTracker bjModel = new GameTracker();
    GameWindow gameWindow = new GameWindow();
    Controller bjControl = new Controller(bjModel, gameWindow);
  } catch (IOException e){
    e.printStackTrace();
    System.out.println("Consider configuring a different server url");
  }
  }
}