package Model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class made to look through the dealer class for errors
 *
 * @author Andin
 */

public class DealerTest {

    /**
     * Test that makes sure that the dealer player is created
     * in a correct manner.
     */
    @Test
    //Test for getting the dealer user
    public void dealerTest(){
        Dealer dealer = new Dealer();
        assertEquals("Dealer", dealer.getUsername());
        assertEquals(9999, dealer.getChips());
    }
}
