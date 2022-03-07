package model;

import model.objects.Card;
import model.objects.Hand;
import model.objects.Player;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class GameTrackerTest {


    /**
     * Testing that creates two players and starts a game, and then
     * tests that there is a next player.
     */
    @Test
    public void playerTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertTrue(testGame.hasNext());
    }

    /**
     * Testing that starts a testGame and makes sure that the turn
     * order is larger than one. This is because if there is one or fewer
     * players in the game it's either only the dealer or no-one playing.
     */
    @Test
    public void nextTurnTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertTrue(testGame.getTurnOrder().size() > 1);
    }

    /**
     * Testing that whenever we use the method to fetch a next card from
     * the deck, it fetches a card and not something else.
     */
    @Test
    public void cardTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertThat(testGame.nextCard(), instanceOf(Card.class));
    }
    /*
    @Test
    //Testing for dealers hand (fixa en public final metod som frågar om dealer namnet)
    public void dealerTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertEquals("Dealer", testGame.getDealerHand().getPlayer().getUsername());
    }*/

    /**
     * Test for fully functional turn order handling. Creating a test game
     * then assigning players to positions and comparing them to the return
     * value from the getter.
     */
    @Test
    //Testing for getting the correct turn order
    public void turnOrderTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        List<Hand> hands = testGame.getTurnOrder();
        assertEquals("Player1", hands.get(0).getPlayer().getUsername());
        assertEquals("Player2", hands.get(1).getPlayer().getUsername());
        assertEquals("Dealer", hands.get(2).getPlayer().getUsername());
    }
}
