package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class made to look through the card class for potential errors or exceptions
 */

public class CardTest {

    @Test
    //Test for Card method and that the requested card is created.
    public void createCard(){
        Card queenOfSpades = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
        assertEquals(Card.Rank.QUEEN, queenOfSpades.getRank());
        assertEquals(Card.Suit.SPADES, queenOfSpades.getSuit());
    }
}
