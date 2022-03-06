package Model.objects;

/**
 * Class for creating players.
 * 
 * @author Andin
 */
public class Player {

    // The name of the user and their amount of chips.
    private final String username;
    private int chips;

    /**
     * Player constructor to define the username
     * and amount of chips for a new player.
     * 
     * @param username The username of player.
     * @param chips The starting amount of chips of player.
     */
    public Player(String username, int chips){
        this.username = username;
        this.chips = chips;
    }

    /**
     * Gets username of player.
     * 
     * @return The username of player.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets amount of chips that player has.
     * 
     * @return The amount of chips that player has.
     */
    public int getChips(){
        return chips;
    }

    /**
     * Removes given amount of chips from players amount of chips.
     * 
     * @return The updated amount of chips that player has.
     */
    public int transactChips(int delta){
        chips += delta;
        return chips;
    }
}
