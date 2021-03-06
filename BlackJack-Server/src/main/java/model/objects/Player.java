package model.objects;

/**
 * Class for creating players.
 *
 * @author Andin
 * @version 2022-03-07
 */
public class Player {

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
     * Adds or removes the given amount of chips from player's chip balance.
     * @param delta The amount to add or subtract to the player's balance
     */
    public void transactChips(int delta){
        chips += delta;
    }
}
