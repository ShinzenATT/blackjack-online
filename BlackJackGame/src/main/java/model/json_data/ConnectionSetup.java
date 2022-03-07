package model.json_data;

/**
 * Handles data player name and room code for a game session.
 * @author Alen
 * @version 2022-03-07
 */
public class ConnectionSetup {
    public final String player_name;
    public final String room_code;

    /**
     * Creates a connection data handler.
     */
    public ConnectionSetup(String player_name, String room_code) {
        this.player_name = player_name;
        this.room_code = room_code;
    }
}
