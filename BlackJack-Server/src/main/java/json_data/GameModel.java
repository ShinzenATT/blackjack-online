package json_data;

import Controller.GameConnection;
import Model.GameTracker;
import Model.objects.Hand;
import Model.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public final String status;
    public final int current_turn;
    public final String room_code;
    public final List<Hand> turn_order;

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
