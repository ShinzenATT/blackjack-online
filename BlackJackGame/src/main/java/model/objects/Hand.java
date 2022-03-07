package model.objects;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class to create a hand of standard playing cards.
 * 
 * @author Joel and Andin
 */
public class Hand {
    
    private final List<Card> hand;
    private final Player association;
    private int bet = 0;
    private boolean active = false;

    /**
     * Hand constructor associates given player with a hand.
     * 
     * @param player The player object to associate the hand with. 
     */
    public Hand(Player player) {
        association = player;
        hand = new ArrayList<>();
    }

    /**
     * The size of a hand.
     * 
     * @return The number of cards in a hand.
     */
    public int numCards() {
        return hand.size();
    }

    /**
     * Calculates the total card value points for a hand.
     * 
     * @return The total points of a hand.
     */
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

    /**
     * The size of hand.
     * 
     * @return The number of cards in a hand.
     */
    public List<Card> getHand() {
        return hand;
    }
    
    /**
     * Adds given card to hand.
     * 
     * @param card The card object to be added to the hand.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Bets given amount of chips.
     * 
     * @param amount The amount of chips to be betted.
     */
    public void betChips(int amount){
        association.transactChips(-1 * amount);
        bet = amount;
    }

    /**
     * Gets the current bet of a hand.
     * 
     * @return The current betted amount.
     */
    public int getBet(){ return bet; }

    /**
     * Toggles whether the hand is currently active or not.
     */
    public void toggleActive(){
        active = !active;
    }

    /**
     * Gets if the hand is active or not.
     * 
     * @return Whether the hand is currently active.
     */
    public boolean getActive() { return active; }

    /**
     * Gets the associated player of a hand.
     * 
     * @return The associated player of that hand.
     */
    public Player getPlayer() { return association; }

    /**
     * Converts a hand object into String. 
     */
    @Override
    public String toString(){ return hand.toString(); }

}
