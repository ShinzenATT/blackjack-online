package Model;

import Model.objects.*;

import java.util.*;

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

    // this is prob not good, but to get started
    public Hand getDealerHand(){
        return turnOrder.get(turnOrder.size()-1);
    }

    // hit logic for dealer?
    /*
    public boolean canHit() {
        boolean canHit = false;
        if(hand.score() < 17 && !hand.busted() && !hand.is21()){
            canHit = true;
        }
        return canHit;
        */
}
