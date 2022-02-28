package Model;

import Controller.GameConnection;
import com.google.gson.Gson;
import com.sun.source.doctree.SeeTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

public class RoomTracker {
    private final Map<String, Room> games = new HashMap<>();

    public Room createRoom(GameConnection gc){
        Random random = new Random();
        String generatedString = random.ints(97, 123)
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        Room r = new Room();
        r.addObserver(gc);

        games.put(generatedString, r);
        return r;
    }

    public Room joinRoom(String roomCode, GameConnection gc){
        Room r = games.get(roomCode);
        if(r == null){
            System.out.println("There's no room with code " + roomCode);
            throw new IllegalArgumentException("Room does not exist");
        }
        r.addObserver(gc);
        return r;
    }

    private class Room extends Observable{
        private final GameTracker gt = null;
        private final Gson gson = new Gson();

        public void StartGame(){

        }

        @Override
        public void notifyObservers() {
            super.notifyObservers(gson.toJson(gt));
        }
    }
}
