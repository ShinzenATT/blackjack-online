package model.objects;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class made to look through the deck class for errors
 *
 * @author Andin
 */


public class DeckTest {

    /**
     * Testing the shuffle method by shuffling two decks
     * and then comparing them.
     *
     * Note: Seeing how overly complicated it would be to achieve a
     * true random for this case. You could increase the accuracy
     * of this test by catching when there is an exception from
     * the expected results in case there would be and edge case.
     */

    @Test
    //Testing that the deck is shuffled in different ways
    public void shuffledDeck(){
        //Lätt shuffle testing
        Deck firstShuffled = new Deck(1, true);
        Deck secondShuffled = new Deck(1, true);
        assertNotEquals(firstShuffled, secondShuffled);
    }

    /**
     * Testing that the deck does not create duplicate cards
     * when creating a deck. This is done by looking through
     * the deckList and filtering duplicates.
     */

    @Test
    //Test made to look through that a deck only creates one type of each card
    public void noDuplicates(){
        Deck deck = new Deck(1, false);
        List deckList = deck.getDeck();
        assertEquals(0, deckList.stream().filter(c -> Collections.frequency(deckList, c) > 1).toList().size());
    }

    /**
     * Testing that the deck is of correct size after removing
     * a card with the next method from deck class.
     */

    @Test
    //Test to check the deck size before compared to after removing a card from the deck
    public void sizeOfDeck(){
        Deck deck = new Deck(1, false);
        int size = deck.getNumCards();
        deck.next();
        assertNotEquals(size, deck.getNumCards());
    }
}
