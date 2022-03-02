package Model.objects;
import java.util.*;

/**
 * Class to create a deck object from standard playing cards.
 * @author Joel
 */
public class Deck implements Iterator<Card>{

    private List<Card> deck = new ArrayList<Card>();

    /**
     * Deck constructor to define number of decks to be used and whether
     * it should be shuffled or not.
     * 
     * @param numDecks Number of decks to be combined
     * @param shuffle Decides if the deck should be shuffled or not
     */
    public Deck(int numDecks, boolean shuffle) {
        for(int i = 0; i < numDecks; i++){
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank,suit));
                //numCardsDeck++;
            }
        }
        }
        if(shuffle){
            Collections.shuffle(deck);
        }
    }

    /**
     * Default constructor for shuffled deck consisting of one deck.
     */
    public Deck(){
        this(1,true);
    }

    /**
     * Returns a card from a deck.
     * @return The next card in a deck.
     */
    @Override
    public Card next(){
        Card drawnCard = this.deck.get(0);
        this.deck.remove(0);
        //numCardsDeck--;
        return drawnCard;
    }

    /**
     * Returns the size of a deck.
     * @return The numer of cards in a deck.
     */
    public int getNumCards(){
        return deck.size();
    }

    /**
     * Returns a list of cards, the deck.
     * @return The deck.
     */
    public List<Card> getDeck(){
        return deck;
    }
    
    /**
     * Checks if a deck has more cards in it.
     * @return Bool whether the deck has more cards in it or not. 
     */
    @Override
    public boolean hasNext() {
        return deck.size() > 0;
    }
}