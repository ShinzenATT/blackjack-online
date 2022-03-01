package Model.objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {


    @Test
    //Testing for correct username adding
    public void usernameTest(){
        Player player = new Player("Tester", 100);
        assertEquals("Tester", player.getUsername());
    }

    @Test
    //Testing for getting the right amount of chips belonging to the player
    public void chipsTest(){
        Player player = new Player("Tester", 100);
        assertEquals(100, player.getChips());
        player.transactChips(-300);
        assertNotEquals(-200, player.getChips());
        assertEquals(0, player.getChips());
    }

}
