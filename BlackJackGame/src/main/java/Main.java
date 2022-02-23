package main.java;

import main.java.Controller.ControllerTest;
import main.java.Model.ModelTest;
import main.java.View.*;

public class Main {

public static void main(String[] args) {

  ModelTest bjModel = new ModelTest();
  GameWindow gameWindow = new GameWindow(bjModel);
  ControllerTest bjControl = new ControllerTest(bjModel, gameWindow);
  }
}