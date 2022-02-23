package main.java.Model;

import main.java.View.GameWindow;

public class ModelTest {

    public Deck gameDeck = new Deck();

    public ModelTest() {
    }

    public Card playerHitCard() {
        return gameDeck.next();
    }

    public void updatePlayerCard() {

    }

}
