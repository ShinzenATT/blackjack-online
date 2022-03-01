package Model;

import Model.objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GameTracker implements Iterator<Hand> {
    private final List<Hand> turnOrder;
    private int turnTracker = 0;
    private Deck deck;

    public GameTracker(Player... players){
        turnOrder = new ArrayList<>();
        for (Player p: players){
            turnOrder.add(new Hand(p));
        }

        Collections.shuffle(turnOrder);

        // Dealer is always last
        turnOrder.add(new Hand(new Dealer()));

        turnOrder.get(0).toggleActive();

        deck = new Deck();

        for (Hand h: turnOrder) {
            h.addCard(deck.next());
            h.addCard(deck.next());
        }

    }

    @Override
    public boolean hasNext() {
        return turnTracker < turnOrder.size();
    }

    // this would probably be stand
    @Override
    public Hand next() {
        if(turnTracker > 0) {
            turnOrder.get(turnTracker - 1).toggleActive();
        }
        turnOrder.get(turnTracker).toggleActive();
        return turnOrder.get(turnTracker++);

    }

    public Card nextCard(){
        return deck.next();
    }

    public List<Hand> getTurnOrder(){
        return Collections.unmodifiableList(turnOrder);
    }

    public int getTurnTracker(){ return turnTracker; }

    public void insertNext(Hand hand){
        turnOrder.add(turnTracker + 1, hand);
    }

}
