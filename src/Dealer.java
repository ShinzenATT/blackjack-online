import java.util.Objects;

import card.hand.deck.Hand;

public class Dealer {

    public String command;

    /**
     * Handles where the cards go and when/how to change them.
     * @param command is the requested command by the specified hand.
     * @param hand is the specified hand that is target to the command.
     */
    public Dealer(String command, Hand hand){
        deal();

        if (Objects.equals(command, "Hit")){
            hit();
        } else if(Objects.equals(command, "Double Down")){
            doubleDown();
        } else if(Objects.equals(command, "Split")){
            split();
        } else if(Objects.equals(command, "Surrender")){
            surrender();
        } else if(Objects.equals(command, "Stand")){
            System.out.println("Stand");
        } else{

        }
    }

    public static void hit(){

    }
    public static void split(){

    }
    public static void doubleDown(){

    }
    public static void surrender(){

    }
    public static void deal(){

    }
}
