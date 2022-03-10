package json_data;

import controller.GameConnection;
import model.GameTracker;
import model.objects.Hand;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds data for a game.
 * @author Alen
 * @version 2022-03-07
 */
public class GameModel {
    public final String status;
    public final int current_turn;
    public final String room_code;
    public final List<Hand> turn_order;

    /**
     * Creates a GameModel object that keeps
     * data about a game.
     * 
     * @param gt GameTracker to follow.
     * @param roomCode The room code of the room.
     */
    public GameModel(GameTracker gt, String roomCode) {
        current_turn = gt.getTurnTracker() - 1;
        turn_order = gt.getTurnOrder();
        room_code = roomCode;

        if(!gt.hasNext()){
            status = "finished";
        } else if(turn_order.get(current_turn).getBet() <= 0){
            status = "betting";
        } else {
            status = "playing";
        }
    }

    /**
     * Creates a GameModel that keeps
     * data about a game.
     * 
     * @param gcList List of game connections.
     * @param roomCode The room code of the room.
     */
    public GameModel(List<GameConnection> gcList, String roomCode){
        status = "waiting";
        current_turn = 0;
        room_code = roomCode;

        turn_order = new ArrayList<>();
        for(GameConnection gc: gcList){
            turn_order.add(new Hand(gc.getPlayer()));
        }
    }
}
