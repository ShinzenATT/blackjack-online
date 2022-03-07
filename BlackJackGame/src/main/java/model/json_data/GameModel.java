package model.json_data;

import model.objects.Hand;
import java.util.List;

/**
 * Class that holds data for a game.
 * @author Alen
 * @version 2022-03-07
 */
public class GameModel {
    public String status;
    public int current_turn;
    public String room_code;
    public List<Hand> turn_order;
}
