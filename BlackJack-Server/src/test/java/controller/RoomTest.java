package controller;

import Controller.Room;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomTest {


    @Test
    //Creating a room
    public void roomTest(){
        Room testRoom = new Room("1111");
        assertThat(testRoom, instanceOf(Room.class));
    }

}
