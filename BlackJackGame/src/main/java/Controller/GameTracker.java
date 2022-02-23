package main.java.Controller;

import main.java.Model.*;
import java.util.*;

public class GameTracker implements Iterator<Hand> {
    private final List<Hand> turnOrder;
    private int turnTracker = 0;
    private final Dealer dealer;

    public GameTracker(Player... players){
        turnOrder = new ArrayList<>();
        for (Player p: players){
            turnOrder.add(new Hand(p));
        }

        Collections.shuffle(turnOrder);

        // Dealer is always last
        dealer = new Dealer();
        turnOrder.add(new Hand(dealer));

        for (Hand h: turnOrder) {
            dealer.deal(h);
        }

    }

    @Override
    public boolean hasNext() {
        return turnTracker < turnOrder.size();
    }

    // this would probably be stand
    @Override
    public Hand next() {
        return turnOrder.get(turnTracker++);

    }
}
