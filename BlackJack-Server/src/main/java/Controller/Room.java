package Controller;

import Model.GameTracker;
import Model.objects.Card;
import Model.objects.Hand;
import Model.objects.Player;
import com.google.gson.Gson;
import json_data.GameModel;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Room extends Observable implements Closeable {
    private GameTracker gt = null;
    private final Gson gson = new Gson();
    private final List<GameConnection> players = new ArrayList<>();
    private final String roomCode;
    private Hand currentTurn = null;

    public Room(String roomCode) {
        super();
        this.roomCode = roomCode;
    }

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
            gc.sendError("Not enought chips to bet", "Not enought chips to bet, consider lowering the bet");
            return;
        }
        currentTurn.betChips(chips);

        notifyObservers();
    }

    public synchronized void hit(GameConnection gc) {
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        }else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        }

        currentTurn.addCard(gt.nextCard());
        if (currentTurn.getPoints() >= 21) {
            stand(gc);
            return;
        }
        notifyObservers();
    }

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

    public synchronized void doubleDown(GameConnection gc){
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        } else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
            return;
        } else if (!currentTurn.canBet(currentTurn.getBet())){
            gc.sendError("Not enought chips to double down", "Not enought chips to double down");
            return;
        }

        currentTurn.betChips(currentTurn.getBet());
        currentTurn.addCard(gt.nextCard());
        stand(gc);
    }

    public synchronized  void split(GameConnection gc){
        if(gt == null){
            gc.sendError("Not started", "Please start the game by sending the 'start' command");
            return;
        } else if (!currentTurn.getPlayer().getUsername().equals(gc.getPlayer().getUsername())) {
            gc.sendError("Not your turn", "Please wait until it is your turn");
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

    public String getRoomCode() {
        return roomCode;
    }

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
