import java.io.FileNotFoundException;

public class CheckWinTest {

    public static boolean runny = true;

    public static void main(String[] args) throws FileNotFoundException {

        String ranks[] = {"A", "2", "3"};
        String suits[] = {"Clubs", "Hearts", "Diamonds"};
        int values[] = {1, 2, 3};

        Deck D = new Deck(ranks, suits, values);

        D.checkWin(D.runny);

        if (Deck.runny == true) System.out.println("Game won!");
        else System.out.println("Game lost!");


    }

}
