package model;

import Controller.GameConnection;
import Controller.Room;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;

/**
 * A class for managing all the room instances
 * @see Room
 */
public class RoomTracker {
    private static final Map<String, Room> games = new HashMap<>();

    /**
     * Creates a room and generates a room code while adding the connection to it
     * @param gc the connection responsible
     * @return the new room
     */
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

    /**
     * Adds the connection to the room
     * @param roomCode the room code related to the room
     * @param gc the connection to be added
     * @return
     */
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

    /**
     * closes all the rooms
     * @see Closeable#close()
     */
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
