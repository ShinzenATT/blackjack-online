package model.json_data;

import model.objects.Hand;

import java.util.List;

public class GameModel {
    public String status;
    public int current_turn;
    public String room_code;
    public List<Hand> turn_order;
}
