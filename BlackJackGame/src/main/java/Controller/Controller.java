package Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.GameTracker;
import Model.ImageDisplayModel;
import Model.objects.Card;
import Model.objects.Hand;
import Model.objects.Player;
import View.GameWindow;

public class Controller {

    private final GameWindow bjview;
    private final GameTracker bjmodel;
    private Hand currentTurn = null;
    private Hand dealerHand = null;

    public Controller(GameTracker bjmodel, GameWindow bjview) {
        this.bjmodel = bjmodel;
        this.bjview = bjview;

        dealerHand = bjmodel.getDealerHand();

        currentTurn = bjmodel.next();
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setPlayerName(currentTurn.getPlayer().getUsername());
        bjview.setHandPoints(currentTurn.getPoints());
        bjview.setPlayerChips(currentTurn.getBet());

        bjview.setupUserCard(getHandImageStrings(currentTurn));

        bjview.setupDealerCard(getHandImageStrings(dealerHand));

    //Controls for hit button
    bjview.addHitButtonListener(e -> {
        Card drawn = bjmodel.nextCard();
        currentTurn.addCard(drawn);
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setHandPoints(currentTurn.getPoints());
        bjview.addUserCard(drawn.toString());
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
    
    private ArrayList<String> getHandImageStrings(Hand h){
        ArrayList<String> imageList = new ArrayList<>();
        for(Card c : h.getHand()){
            imageList.add(c.toString());
            System.out.println(c.toString());
        }
        return imageList;
    }
}
