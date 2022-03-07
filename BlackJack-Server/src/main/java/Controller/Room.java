package Controller;

import model.GameTracker;
import model.objects.Card;
import model.objects.Hand;
import model.objects.Player;
import com.google.gson.Gson;
import json_data.GameModel;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class acts as an instance game of blackjack and handles all the actions requested while updating a {@link GameTracker} object accordingly.
 * This class also accepts observers and updates them when {@link GameTracker} is updated.
 * @see Observable
 * @see GameTracker
 * @see GameConnection
 * @see model.RoomTracker
 */
public class Room extends Observable implements Closeable {
    private GameTracker gt = null;
    private final Gson gson = new Gson();
    private final List<GameConnection> players = new ArrayList<>();
    private final String roomCode;
    private Hand currentTurn = null;

    /**
     * Creates a room instance with a room code and does some preparations for a game start.
     * @param roomCode the room code that leads to this instance
     */
    public Room(String roomCode) {
        super();
        this.roomCode = roomCode;
    }

    /**
     * Starts a game of black jack by creating a gameTracker and starts the first turn.
     * @param gc the connection calling this command
     * @see GameTracker#GameTracker(Player...)
     * @see GameTracker#next()
     * @see Observable#notifyObservers()
     */
    public synchronized void startGame(GameConnection gc) {
        if(gt != null && gt.hasNext()){
            gc.sendError("game already in progress", "Please finish the game before starting a new one");
            return;
        }

        List<Player> a = new ArrayList<>();

        for (GameConnection c : players) {
            if(!c.isClosed()){
                a.add(c.getPlayer());
            }
        }

        gt = new GameTracker(a.toArray(new Player[a.size()]));
        System.out.println(gt);

        currentTurn = gt.next();
        System.out.println(currentTurn);
        notifyObservers();
    }

    /**
     * Sets a bet to the current {@link Hand} and removes the amount from the {@link Player} balance
     * @param chips the amount chips bet
     * @param gc the connection sending this command
     * @see Hand#betChips(int)
     * @see Player#transactChips(int)
     * @see Observable#notifyObservers()
     */
    public synchronized void bet(int chips, GameConnection gc) {
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        }else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        } else if (currentTurn.getBet() > 0) {
            gc.sendError("Bet already placed", "The bet has already been placed, consider using double down instead");
            return;
        } else if (!currentTurn.canBet(chips)){
            gc.sendError("Not enough chips to bet", "Not enough chips to bet, consider lowering the bet");
            return;
        }
        currentTurn.betChips(chips);

        notifyObservers();
    }

    /**
     * Adds a new card to the hand from the deck. If the hand point total is 21 or above a stand is automatically executed.
     * @param gc the connection sending this command
     * @see GameTracker#nextCard()
     * @see Room#stand(GameConnection)
     * @see Observable#notifyObservers()
     */
    public synchronized void hit(GameConnection gc) {
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        }else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        } else if (currentTurn.getBet() <= 0){
            gc.sendError("No bet", "You must bet before you can do any action");
            return;
        }

        currentTurn.addCard(gt.nextCard());
        if (currentTurn.getPoints() >= 21) {
            stand(gc);
            return;
        }
        notifyObservers();
    }

    /**
     * The current turn ends and the next hand in the turnorder start its turn. If the hand belongs to the dealer then the ai starts.
     * If the turnorder reaches its end then all the winning are dealt to the players.
     * @param gc the connection sending this command
     * @see GameTracker#next()
     * @see Room#runDealerAI()
     * @see Observable#notifyObservers()
     */
    public synchronized void stand(GameConnection gc) {
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        } else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        }

        currentTurn = gt.next();
        if(!gt.hasNext()){
            runDealerAI();
            for (Hand h : gt.getTurnOrder()) {
                if(currentTurn.getPoints() > 21){ // dealer went bust
                    h.getPlayer().transactChips(h.getBet() + (h.getBet()/2));
                } else if(h.getPoints() > currentTurn.getPoints() && !(h.getPoints() > 21)){ // player won, more points
                    h.getPlayer().transactChips(h.getBet() + (h.getBet()/2)); 
                } else if(currentTurn.getPoints() == h.getPoints()){ // draw between dealer and player
                    h.getPlayer().transactChips(h.getBet());
                }
            }
        }
        notifyObservers();
    }

    /**
     * The current hand does one hit and afterwards does a stand. The bet is multiplied by 1.5x while doing this.
     * @param gc the connection sending this command
     * @see Room#hit(GameConnection)
     * @see Room#stand(GameConnection)
     * @see Hand#betChips(int)
     * @see Observable#notifyObservers()
     */
    public synchronized void doubleDown(GameConnection gc){
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        } else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        }else if (currentTurn.getBet() <= 0){
            gc.sendError("No bet", "You must best before you can do any action");
            return;
        }  else if (!currentTurn.canBet(currentTurn.getBet())){
            gc.sendError("Not enough chips to double down", "Not enough chips to double down");
            return;
        } else if(currentTurn.numCards() > 2){
            gc.sendError("Hit already done", "Double down is unavailable when a hit has been done");
            return;
        }

        currentTurn.betChips(currentTurn.getBet());
        currentTurn.addCard(gt.nextCard());
        stand(gc);
    }

    /**
     * The current hand that has two cards of the same rank can split the card into two separate hands for the same player.
     * The other hand is set right after the current hand in the turn order.
     * @param gc The connection sending this command
     * @see Hand#getAndRemoveLastCard()
     * @see Hand#addCard(Card)
     * @see GameTracker#insertNext(Hand)
     * @see Observable#notifyObservers()
     */
    public synchronized  void split(GameConnection gc){
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        } else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        } else if (currentTurn.getBet() <= 0){
            gc.sendError("No bet", "You must best before you can do any action");
            return;
        } else if(currentTurn.getHand().size() != 2 || currentTurn.getHand().get(0).getRank() != currentTurn.getHand().get(1).getRank()){
            gc.sendError("hand not viable", "The cards must match in rank to split");
            return;
        }

        Card c = currentTurn.getAndRemoveLastCard();
        Hand h = new Hand(currentTurn.getPlayer());
        h.addCard(c);
        gt.insertNext(h);

        notifyObservers();
    }

    /**
     * An algorithm is run for the current hand where it hits until the hand value is 17 or above and then stands.
     * @see Hand#addCard(Card)
     * @see Hand#getPoints()
     * @see GameTracker#nextCard()
     */
    public synchronized void runDealerAI(){
        while (currentTurn.getPoints() < 17){
            currentTurn.addCard(gt.nextCard());
        }
    }

    @Override
    public void notifyObservers() {
        setChanged();
        if (gt != null) {
            super.notifyObservers(gson.toJson(new GameModel(gt, roomCode)));
        } else {
            super.notifyObservers(gson.toJson(new GameModel(players, roomCode)));
        }
    }

    /**
     * Adds an observer and also adds the connection to the list of players
     * @param gc the connection to be added as a player in the next game
     * @see Observable#addObserver(Observer)
     */
    public void addObserver(GameConnection gc) {
        super.addObserver(gc);
        players.add(gc);
        notifyObservers();
    }

    @Override
    public void close() throws IOException {
        for (GameConnection c : players) {
            c.close();
        }

        System.out.println("Closed room " + this);
    }

    /**
     * @return The room code associated with the current instance
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * Checks if a player with an active connection with the requested player nmae is in the room
     * @param playerName the name to be checked
     * @return a boolean if that player name is used
     */
    public boolean playerExists(String playerName){
        if(players.stream().anyMatch(gc -> gc.getPlayer().getUsername().equals(playerName))){
            boolean isConnected = false;
            for(GameConnection gc : players.stream().filter(gc -> gc.getPlayer().getUsername().equals(playerName)).toList()){
                if(!gc.isClosed()){
                    isConnected = true;
                }
            }
            return isConnected;
        }
        return false;
    }
}
