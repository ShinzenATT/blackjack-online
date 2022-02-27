package Model.objects;
import java.util.*;

public class Deck implements Iterator<Card>{

    private List<Card> deck = new ArrayList<Card>();
    //private int numCardsDeck;

    /**
     * Deck constructor to define number of decks to be used and whether
     * it should be shuffled or not.
     * 
     * @param numDecks number of decks to be combined
     * @param shuffle decides if the deck should be shuffled or not
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

    @Override
    public Card next(){
        Card drawnCard = this.deck.get(0);
        this.deck.remove(0);
        //numCardsDeck--;
        return drawnCard;
    }

    public int getNumCards(){
        return deck.size();
    }

    // för testning behövs nog inte sen
    public List<Card> getDeck(){
        return deck;
    }

    @Override
    public boolean hasNext() {
        return deck.size() > 0;
    }
}