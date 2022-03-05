package model;

import model.objects.Hand;
import model.objects.Player;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class GameTrackerTest {


    @Test
    //Testing for next player
    public void playerTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertTrue(testGame.hasNext());
    }

    @Test
    //Testing for a turn order
    public void nextTurnTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertTrue(testGame.getTurnOrder().size() > 1);
    }


    @Test
    //Testing for next card (fixa så att den kollar Card och inte had)
    public void cardTest(){
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        GameTracker testGame = new GameTracker(player1, player2);
        assertThat(testGame.nextCard(), instanceOf(Hand.class));
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
