import controller.Controller;
import model.GameTracker;
import view.*;

import java.io.IOException;

public class Main {

public static void main(String[] args) {

  try {
    GameTracker bjModel = new GameTracker();
    GameWindow gameWindow = new GameWindow();
    Controller bjControl = new Controller(bjModel, gameWindow);
  } catch (IOException e){
    e.printStackTrace();
    System.out.println("consider configuring a different server url");
  }
  }
}