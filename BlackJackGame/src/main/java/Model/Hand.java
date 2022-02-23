package main.java.Model;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hand {
    
    private final List<Card> hand;
    private final Player association;
    private int bet = 0;

    public Hand(Player player) {
        association = player;
        hand = new ArrayList<>();
    }

    public int numCards() {
        return hand.size();
    }

    public int getPoints(){
        // They are atomics in order to be used in lambdas and switch cases
        AtomicInteger sum = new AtomicInteger();
        AtomicInteger aces = new AtomicInteger();

        hand.forEach((c) -> {
            switch (c.getRank()) {

                case JACK, QUEEN, KING -> sum.addAndGet(10);
                // Aces can either be 11 or 1 so all aces are 11 until the hand goes over 21
                case ACE -> {
                    if(sum.get() + 11 <= 21) {
                        aces.incrementAndGet();
                        sum.addAndGet(11);
                    } else {
                        sum.incrementAndGet();
                    }
                }
                default -> sum.addAndGet(c.getRank().ordinal() + 2);
            }
        });

        // Reduce any aces to one if the current hand value is above 21
        for(; sum.get() > 21 && aces.get() > 0; aces.decrementAndGet()){
            sum.addAndGet(-10);
        }

        return sum.get();
    }

    public List<Card> getHand() {
        return hand;
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }

    public void betChips(int amount){
        association.transactChips(-1 * amount);
        bet = amount;
    }

    public int getBet(){ return bet; }

}
