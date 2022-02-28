package Model.objects;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class DeckTest {


    @Test
    //Suffled
    public void shuffledDeck(){
        //Lätt shuffle testing
        Deck firstShuffled = new Deck(1, true);
        Deck secondShuffled = new Deck(1, true);
        assertNotEquals(firstShuffled, secondShuffled);
    }

    @Test
    //no duplicates
    public void noDouplicates(){
        Deck deck = new Deck(1, false);
        List deckList = deck.getDeck();
        assertEquals(0, deckList.stream().filter(c -> Collections.frequency(deckList, c) > 1).toList().size());
    }
    @Test
    //drawing card reduces size of remaining deck
    public void sizeOfDeck(){
        Deck deck = new Deck(1, false);
        int size = deck.getNumCards();
        deck.next();
        assertNotEquals(size, deck.getNumCards());
    }
}
