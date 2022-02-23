import card.hand.deck.Deck;
import card.hand.deck.Hand;
import card.hand.deck.Player;

public class Dealer extends Player {
    public enum commands { HIT, DOUBLE_DOWN, SPLIT, SURRENDER, STAND };
    private final Deck deck;

    public Dealer() {
        super("Dealer", 9999);
        deck = new Deck();
    }

    /**
     * Handles where the cards go and when/how to change them.
     * @param command is the requested command by the specified hand.
     * @param hand is the specified hand that is target to the command.
     */
    public void act(commands command, Hand hand){

        switch (command){
            case HIT -> hit(hand);
            case SPLIT -> split();
            case DOUBLE_DOWN -> doubleDown();
            case SURRENDER -> surrender();
            case STAND -> System.out.println("stand");
        }
    }

    public void hit(Hand hand){
        hand.addCard(deck.next());
    }
    public static void split(){

    }
    public static void doubleDown(){

    }
    public static void surrender(){

    }
    public Hand deal(Hand hand){
        hand.addCard(deck.next());
        hand.addCard(deck.next());
        return hand;
    }
}
