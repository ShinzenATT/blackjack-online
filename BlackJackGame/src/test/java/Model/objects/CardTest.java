package Model.objects;

import org.junit.Test;

import static org.junit.Assert.*;


public class CardTest {

    @Test
    public void createCard(){
        Card queenOfSpades = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);
        assertEquals(Card.Rank.QUEEN, queenOfSpades.getRank());
        assertEquals(Card.Suit.SPADES, queenOfSpades.getSuit());
    }
}
