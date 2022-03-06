package model.objects;

public class Player {

    private final String username;
    private int chips;

    public Player(String username, int chips){
        this.username = username;
        this.chips = chips;
    }

    public String getUsername(){
        return username;
    }

    public int getChips(){
        return chips;
    }

    public void transactChips(int delta){
        chips += delta;
    }
}
