import card.hand.deck.Deck;

public class Main {

public static void main(String[] args) {

    // testning
    Deck test = new Deck(1, true);
    System.out.println("Orginal: " + test.getDeck());
    System.out.println("Num of cards:" + test.getNumCards());
    System.out.println();
    System.out.println("Drawn card: " + test.getCard());
    System.out.println();
    System.out.println("Edited: " + test.getDeck());
    System.out.println("Num of cards:" + test.getNumCards());
  }
}