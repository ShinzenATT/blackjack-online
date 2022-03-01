package Model.objects;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class made to look through the deck class for errors
 */


public class DeckTest {



    @Test
    //Testing that the deck is shuffled in different ways
    public void shuffledDeck(){
        //Lätt shuffle testing
        Deck firstShuffled = new Deck(1, true);
        Deck secondShuffled = new Deck(1, true);
        assertNotEquals(firstShuffled, secondShuffled);
    }

    @Test
    //Test made to look through that a deck only creates one type of each card
    public void noDuplicates(){
        Deck deck = new Deck(1, false);
        List deckList = deck.getDeck();
        assertEquals(0, deckList.stream().filter(c -> Collections.frequency(deckList, c) > 1).toList().size());
    }

    @Test
    //Test to check the deck size before compared to after removing a card from the deck
    public void sizeOfDeck(){
        Deck deck = new Deck(1, false);
        int size = deck.getNumCards();
        deck.next();
        assertNotEquals(size, deck.getNumCards());
    }
}
