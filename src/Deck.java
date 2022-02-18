import java.util.*;

public class Deck{

    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank,suit));
            }
        }
    }

    public ArrayList<Card> newDeck() {
        return new ArrayList<Card>(deck);
    }

    public void shuffle() {
        Collections.shuffle(this.deck); 
    }
}