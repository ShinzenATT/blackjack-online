package model;

import model.json_data.GameModel;
import model.objects.*;

import java.util.*;

/**
 * Class that keeps track of the current game of blackjack.
 * @author Alen
 * @version 2022-03-07
 */
public class GameTracker {
    private List<Hand> turnOrder;
    private int turnTracker = 0;
    private String roomCode = "";
    private String status = "";

    /**
     * Creates a GameTracker object.
     */
    public GameTracker(){
        turnOrder = new ArrayList<>();
    }

    
    /** 
     * Updates data about the current game.
     * 
     * @param gm The GameModel object of the current game.
     */
    public synchronized void updateData(GameModel gm){
        status = gm.status;
        turnTracker = gm.current_turn;
        turnOrder = gm.turn_order;
        roomCode = gm.room_code;
    }

    
    /** 
     * Checks if the game has more rounds.
     * 
     * @return Check result if game has more rounds.
     */
    public boolean hasNext() {
        return turnTracker < turnOrder.size() && !Objects.equals(status, "finished");
    }

    
    /** 
     * Gets the roomcode of the current turn.
     * 
     * @return The roomcode of the current game.
     */
    public String getRoomCode(){
        return roomCode;
    }

    
    /** 
     * Gets the hand of the current turns player.
     * 
     * @return Hand
     */
    public Hand getCurrentTurn() {
        if(hasNext()) {
            return turnOrder.get(turnTracker);
        } else {
            return turnOrder.get(turnTracker-1);
        }
    }

    
    /** 
     * Gets the turnorder of the game.
     * 
     * @return The turn order of the game.
     */
    public List<Hand> getTurnOrder(){
        return Collections.unmodifiableList(turnOrder);
    }

    
    /** 
     * Gets dealers current hand.
     * 
     * @return Dealers hand.
     */
    public Hand getDealerHand(){
        return turnOrder.get(turnOrder.size()-1);
    }

    /**
     * Gets the turntracker.
     * 
     * @return The turntracker of the game.
     */
    public int getTurnIndex(){ return turnTracker; }


    /**
     * Converts the data of the current game into a string.
     */
    @Override
    public String toString() {
        return "GameTracker{" +
                "turnOrder=" + turnOrder +
                ", turnTracker=" + turnTracker +
                ", roomCode='" + roomCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
