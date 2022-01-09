import java.io.FileNotFoundException;

public class CardTest {

    public static void main(String[] args) throws FileNotFoundException {

        String ranks[] = {"A", "2"};
        String suits[] = {"Clubs", "Hearts"};
        int values[] = {1, 2};

        Deck D = new Deck(ranks, suits, values);

        D.dealtCards();

    }

}