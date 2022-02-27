package Controller;

import Model.GameTracker;
import Model.objects.Hand;
import View.GameWindow;

public class ControllerTest {

    private final GameWindow bjView;
    private final GameTracker bjModel;
    private Hand currentTurn = null;

    public ControllerTest(GameTracker bjmodel, GameWindow bjview) {
        this.bjModel = bjmodel;
        this.bjView = bjview;

        currentTurn = bjmodel.next();
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setPlayerName(currentTurn.getPlayer().getUsername());
        bjview.setHandPoints(currentTurn.getPoints());
        bjview.setPlayerChips(currentTurn.getBet());

    //Controls for hit button
    bjView.addHitButtonListener(e -> {
        currentTurn.addCard(bjmodel.nextCard());
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setHandPoints(currentTurn.getPoints());
    });

    // Controls for stand button
    bjview.addStandButtonListener(e -> {
        if(bjmodel.hasNext()) {
            currentTurn = bjmodel.next();
            bjview.getDrawnCardLabel().setText(currentTurn.toString());
            bjview.setPlayerName(currentTurn.getPlayer().getUsername());
            bjview.setHandPoints(currentTurn.getPoints());
            bjview.setPlayerChips(currentTurn.getBet());
        } else {
            bjview.getDrawnCardLabel().setText("End of Round");
            bjview.setPlayerName("");
            bjview.setHandPoints(0);
            bjview.setPlayerChips(0);
        }
    });
    }
}
