package main.java.Controller;

import java.awt.event.*;

import main.java.Model.ModelTest;
import main.java.View.GameWindow;

public class ControllerTest {

    private final GameWindow bjView;
    private final ModelTest bjModel;

    public ControllerTest(ModelTest bjmodel, GameWindow bjview) {
        this.bjModel = bjmodel;
        this.bjView = bjview;

    //Controls for hit button
    class HitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            bjview.getDrawnCardLabel().setText(bjmodel.playerHitCard().toString());
        }
    
    }

    bjView.addHitButtonListener(new HitButtonListener());  
    }
}
