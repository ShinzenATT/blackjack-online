package Model;

import Controller.GameConnection;
import Model.objects.Hand;
import Model.objects.Player;
import com.google.gson.Gson;
import json_data.GameModel;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

public class RoomTracker {
    private static final Map<String, Room> games = new HashMap<>();

    public static Room createRoom(GameConnection gc){
        Random random = new Random();
        String generatedString = random.ints(97, 123)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        Room r = new Room(generatedString);
        r.addObserver(gc);

        games.put(generatedString, r);
        return r;
    }

    public static Room joinRoom(String roomCode, GameConnection gc){
        Room r = games.get(roomCode);
        if(r == null){
            System.out.println("There's no room with code " + roomCode);
            throw new IllegalArgumentException("Room does not exist");
        }
        r.addObserver(gc);
        return r;
    }

    public static void close() {
        games.forEach((s, e) -> {
            try {
                e.close();
            }catch (IOException err){
                System.out.println("Failed to close  " + e);
                err.printStackTrace();
            }
        });
    }

    public static class Room extends Observable implements Closeable {
        private GameTracker gt = null;
        private final Gson gson = new Gson();
        private final List<GameConnection> players = new ArrayList<>();
        private final String roomCode;
        private Hand currentTurn = null;

        public Room(String roomCode){
            super();
            this.roomCode = roomCode;
        }

        public void startGame(){
            List<Player> a = new ArrayList<>();

            for (GameConnection c: players) {
                a.add(c.getPlayer());
            }

            gt = new GameTracker(a.toArray(new Player[a.size()]));

            currentTurn  = gt.next();

            setChanged();
            notifyObservers();
        }

        public void bet(int chips, GameConnection gc){
            if(currentTurn.getPlayer() != gc.getPlayer()){
                gc.sendError("Not your turn", "Please wait until it is your turn");
                return;
            }
            currentTurn.betChips(chips);
        }

        @Override
        public void notifyObservers() {
            setChanged();
            if(gt != null) {
                super.notifyObservers(gson.toJson(new GameModel(gt, roomCode)));
            } else {
                super.notifyObservers(gson.toJson(new GameModel(players, roomCode)));
            }
        }

        public synchronized void addObserver(GameConnection gc) {
            super.addObserver(gc);
            players.add(gc);
            notifyObservers();
        }

        @Override
        public void close() throws IOException {
            for (GameConnection c: players){
                c.close();
            }

            System.out.println("Closed room " + this);
        }

        public String getRoomCode() { return roomCode; }
    }
}
