package controller;

import Controller.Room;
import Controller.SocketManager;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Test class for room methods
 *
 * @author Andin
 */
public class RoomTest {


    /**
     * Creates a room with the room code "1111"
     * then it checks that the created room is of the room class
     */
    @Test
    //Creating a room
    public void roomTest(){
        Room testRoom = new Room("1111");
        assertThat(testRoom, instanceOf(Room.class));
    }


    @Test
    //Testing the stand function
    public void startGameTest() {
        try{
            SocketManager.Start();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
