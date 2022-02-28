package Model.objects;

import org.junit.Test;


public class CardTest {


    @Test
    public void cardMethod(){
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                System.out.println(new Card(rank,suit));
            }
        }
    }
}
