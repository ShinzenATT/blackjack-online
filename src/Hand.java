import java.util.ArrayList;

public class Hand {
    
    ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public int numCards() {
        return hand.size();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }

}
