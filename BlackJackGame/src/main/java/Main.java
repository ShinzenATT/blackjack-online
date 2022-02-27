package main.java;

import main.java.Controller.ControllerTest;
import main.java.Model.GameTracker;
import main.java.Model.objects.Player;
import main.java.View.*;

public class Main {

public static void main(String[] args) {

  GameTracker bjModel = new GameTracker(
          new Player("Player 1", 1000),
          new Player("Player 2", 1000),
          new Player("Player 3", 1000)
  );
  GameWindow gameWindow = new GameWindow();
  ControllerTest bjControl = new ControllerTest(bjModel, gameWindow);
  }
}