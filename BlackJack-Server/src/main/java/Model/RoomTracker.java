package Model;

import Controller.GameConnection;
import Controller.Room;

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
            gc.sendError("Room not exist", "The room was not found, did you enter the right room code?");
            throw new IllegalArgumentException();
        } else if(r.playerExists(gc.getPlayer().getUsername())){
            gc.sendError("username taken", "the username is already taken in that room, please use a different name");
            throw new IllegalArgumentException();
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

}
