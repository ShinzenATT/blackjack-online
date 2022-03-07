package Model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class made to look through the player class for errors
 *
 * @author Andin
 */

public class PlayerTest {

    /**
     * Testing that the correct username is set when creating a
     * player.
     */
    @Test
    public void usernameTest(){
        Player player = new Player("Tester", 100);
        assertEquals("Tester", player.getUsername());
    }

    /**
     * Testing that a player has the amount of chips before and
     * after transactions. Also tests that the correct value is
     * returned when it should be zero.
     */
    @Test
    public void chipsTest(){
        Player player = new Player("Tester", 100);
        assertEquals(100, player.getChips());
        player.transactChips(-31);
        assertEquals(69, player.getChips());
        player.transactChips(-69);
        assertEquals(0, player.getChips());
    }

}
