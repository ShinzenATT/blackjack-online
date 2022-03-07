package model.objects;

/**
 * Class to create standard playing card objects.
 * @author Joel
 * @version 2022-03-07
 * 
 */
public class Card {

    /**
     * Enum for the different ranks that a standard
     * playing card can be.
     */
    public enum Rank { TWO, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE }
    
    /**
     * Enum for the different French suits that a standard
     * playing card can be.
     */
    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    private Rank rank;
    private Suit suit;

    /**
     * Creates a card with the specified rank and suit.
     * 
     * @param rank The rank to be set to the card.
     * @param suit The suit to be set to the card.
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * The rank of the card object.
     * @return Rank of the card.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * The suit of the card object.
     * @return Suit of the card.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Converts the enums of the object to a String.
     */
    public String toString() {
        return rank + "_of_" + suit; 
    }

}