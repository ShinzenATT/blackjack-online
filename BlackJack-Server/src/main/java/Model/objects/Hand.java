package Model.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Hand {
    
    private final List<Card> hand;
    private final Player association;
    private int bet = 0;
    private boolean active = false;

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

    public boolean canBet(int amount){
        if(association.getChips() - amount < 0 || amount <= 0){
            return false;
        } else{
            return true;
        }
    }

    public int getBet(){ return bet; }

    public void toggleActive(){
        active = !active;
    }

    public boolean getActive() { return active; }

    public Player getPlayer() { return association; }

    public Card getAndRemoveLastCard(){
        Card c = hand.get(hand.size() - 1);
        hand.remove(hand.size() - 1);
        return c;
    }

    @Override
    public String toString(){ return hand.toString(); }

}
