public class Main {

public static void main(String[] args) {
    Deck test = new Deck();
    System.out.println("Orginal: " + test.newDeck());
    test.shuffle();
    System.out.println("Shuffeled: " + test.newDeck());
  }
}