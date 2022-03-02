package Model.json_data;

import Model.GameTracker;
import Model.objects.Hand;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public String status;
    public int current_turn;
    public String room_code;
    public List<Hand> turn_order;
}
