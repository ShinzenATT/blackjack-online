package card.hand.deck;
import java.util.*;

public class Hand {
    
    private List<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public int numCards() {
        return hand.size();
    }

    public List<Card> getHand() {
        return hand;
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }

}
