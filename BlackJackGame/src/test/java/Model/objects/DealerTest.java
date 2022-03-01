package Model.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DealerTest {


    @Test
    //Test for getting the dealer user
    public void dealerTest(){
        Dealer dealer = new Dealer();
        assertEquals("Dealer", dealer.getUsername());
        assertEquals(9999, dealer.getChips());
    }
}
