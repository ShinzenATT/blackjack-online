package model;

import model.json_data.GameModel;
import model.objects.*;

import java.util.*;

public class GameTracker {
    private List<Hand> turnOrder;
    private int turnTracker = 0;
    private String roomCode = "";
    private String status = "";

    public GameTracker(){
        turnOrder = new ArrayList<>();
    }

    public synchronized void updateData(GameModel gm){
        status = gm.status;
        turnTracker = gm.current_turn;
        turnOrder = gm.turn_order;
        roomCode = gm.room_code;
    }

    public boolean hasNext() {
        return turnTracker < turnOrder.size() && !Objects.equals(status, "finished");
    }

    public String getRoomCode(){
        return roomCode;
    }

    // this would probably be stand
    public Hand getCurrentTurn() {
        if(hasNext()) {
            return turnOrder.get(turnTracker);
        } else {
            return turnOrder.get(turnTracker-1);
        }
    }

    public List<Hand> getTurnOrder(){
        return Collections.unmodifiableList(turnOrder);
    }

    // this is prob not good, but to get started
    public Hand getDealerHand(){
        return turnOrder.get(turnOrder.size()-1);
    }

    public int getTurnIndex(){ return turnTracker; }

    // hit logic for dealer?
    /*
    public boolean canHit() {
        boolean canHit = false;
        if(hand.score() < 17 && !hand.busted() && !hand.is21()){
            canHit = true;
        }
        return canHit;
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
