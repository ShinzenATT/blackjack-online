package model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class made to look through the Hand class for errors
 *
 * @author Andin
 */

public class HandTest {

    /**
     * Testing the points of cards inside a hand by adding cards
     * to a hand and using the getPoints function and comparing
     * it to the expected value.
     * Also testing the points depending on how many aces and
     * how they behave depending on what kind of hand you have.
     */
    @Test
    public void pointTest(){
        //Creating a tester user with 10 chips and adding cards to his hand with increasing value
        Player tester = new Player("Tester", 10);
        Hand hand = new Hand(tester);
        hand.addCard(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        assertEquals(4, hand.getPoints());

        //Expected ace to be valued 11 and not 1
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        assertEquals(15, hand.getPoints());

        //Expected for the ace to change value from 11 to 1 because it exceeds 21 otherwise
        hand.addCard(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        assertNotEquals(25, hand.getPoints());

        //Expected both aces to be valued 1 and not 11 since there are now two of them
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        assertNotEquals(36, hand.getPoints());
        assertNotEquals(26, hand.getPoints());
    }

    /**
     * Testing that the getPlayer returns the owner of the
     * requested hand by creating a test player and a hand
     * then comparing the returned results to the preset
     */
    @Test
    public void handOwnerTest(){
        Player tester = new Player("Tester", 10);
        Hand hand = new Hand(tester);
        assertEquals(tester, hand.getPlayer());
    }

    /**
     * Testing that the method for getting current handSize
     * is accurate. This by first getting and empty hand and
     * then adding cards as you go.
     */
    @Test
    public void handSizeTest(){
        Player tester = new Player("Tester", 10);
        Hand hand = new Hand(tester);

        //First make sure it's an empty hand
        assertEquals(0, hand.getHand().size());

        //Add a card and test if it has one card
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        assertEquals(1, hand.getHand().size());

        //Now add 4 more cards and test if it has five
        hand.addCard(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        assertEquals(5, hand.getHand().size());
    }

    /**
     * Testing that the method returning amount of chips bet
     * returns the correct amount.
     */
    @Test
    public void chipValueTest(){
        Player tester = new Player("Tester", 199);
        Hand hand = new Hand(tester);

        //Test that the chips requested to bet is the amount that is bet
        hand.betChips(69);
        assertEquals(69, hand.getBet());
    }
}
