public class Player {

    private String username;
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
}
