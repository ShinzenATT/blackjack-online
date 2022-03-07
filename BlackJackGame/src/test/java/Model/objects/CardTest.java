package Model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class made to look through the card class for potential errors or exceptions
 *
 * @author Andin
 */

public class CardTest {

    /**
     * Testing the method for creating cards by
     * running through them one by one.
     */
    @Test
    public void createCard(){
        for(Card.Rank rank: Card.Rank.values() ){
            for(Card.Suit suit: Card.Suit.values()){
                Card queenOfSpades = new Card(rank, suit);
                assertEquals(rank, queenOfSpades.getRank());
                assertEquals(suit, queenOfSpades.getSuit());
            }
        }
    }
}
