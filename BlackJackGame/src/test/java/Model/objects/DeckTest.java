package Model.objects;

import org.junit.Test;

public class DeckTest {


    @Test
    public void deckMethod(){
        //First test one unshuffled deck
        try{
            Deck one = new Deck(1, false);
            System.out.println("One unshuffled deck has " + one.getNumCards() + "cards.");
        }catch(Exception e){
            System.out.println("One unshuffled exception: " + e);
        }

        try{
            Deck two = new Deck(6, true);
            System.out.println("Six shuffled deck has " + two.getNumCards() + "cards.");
        }catch(Exception e){
            System.out.println("6 shuffled exception: " + e);
        }
    }
}
