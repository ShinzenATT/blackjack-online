package Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.GameTracker;
import Model.ImageDisplayModel;
import Model.SoundEffectModel;
import Model.objects.Card;
import Model.objects.Dealer;
import Model.objects.Hand;
import Model.objects.Player;
import View.GameWindow;

public class Controller {

    private final GameWindow bjview;
    private final GameTracker bjmodel;
    private final SoundEffectModel soundmodel = null;
    private Hand currentTurn = null;
    private Hand dealerHand = null;

    public Controller(GameTracker bjmodel, GameWindow bjview) {
        this.bjmodel = bjmodel;
        this.bjview = bjview;

        dealerHand = bjmodel.getDealerHand();

        currentTurn = bjmodel.next();
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setPlayerName(currentTurn.getPlayer().getUsername());
        bjview.setPlayerHandPoints(currentTurn.getPoints());
        //bjview.setDealerHandPoints(dealerHand.getPoints()); //sätts i slutet ist
        bjview.setPlayerChips(currentTurn.getBet());

        bjview.setupUserCard(getHandImageStrings(currentTurn));

        bjview.setupDealerCard(getHandImageStrings(dealerHand), true); // true, hide first card

    //Controls for hit button
    bjview.addHitButtonListener(e -> {
        Card drawn = bjmodel.nextCard();
        currentTurn.addCard(drawn);
        bjview.getDrawnCardLabel().setText(currentTurn.toString());
        bjview.setPlayerHandPoints(currentTurn.getPoints());
        bjview.addUserCard(drawn.toString());
        soundmodel.playSound("dealCard");
    });

    // Controls for stand button
    bjview.addStandButtonListener(e -> {
        if(bjmodel.hasNext()) {
            currentTurn = bjmodel.next();
            bjview.getDrawnCardLabel().setText(currentTurn.toString());
            bjview.setPlayerName(currentTurn.getPlayer().getUsername());
            bjview.setPlayerHandPoints(currentTurn.getPoints());
            bjview.setPlayerChips(currentTurn.getBet());
            if(currentTurn.getPlayer().getClass() == Dealer.class){
                while(currentTurn.getPoints() <= 16){
                    currentTurn.addCard(bjmodel.nextCard());
                }
                bjview.clearDealerLabels();
                bjview.setupDealerCard(getHandImageStrings(dealerHand), false);
                bjview.setDealerHandPoints(currentTurn.getPoints());
            }
        } else {
            bjview.getDrawnCardLabel().setText("End of Round");
            bjview.setPlayerName("");
            bjview.setPlayerHandPoints(0);
            bjview.setPlayerChips(0);
        }
    });
    }
    
    private ArrayList<String> getHandImageStrings(Hand h){
        ArrayList<String> imageList = new ArrayList<>();
        for(Card c : h.getHand()){
            imageList.add(c.toString());
        }
        return imageList;
    }
}
