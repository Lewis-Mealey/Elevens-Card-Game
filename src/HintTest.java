import java.io.FileNotFoundException;

public class HintTest {

    public static void main(String[] args) throws FileNotFoundException {

        String ranks[] = {"A", "2", "3"};
        String suits[] = {"Clubs", "Hearts", "Diamonds"};
        int values[] = {1, 2, 3};

        Deck D = new Deck(ranks, suits, values);

        D.dealtCards();

    }

}
