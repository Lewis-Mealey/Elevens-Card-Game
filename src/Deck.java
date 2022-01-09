import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

public class Deck {

    public static boolean runny = true;

    private ArrayBag<Card> cards;
    public int size;

    public Deck(String[] ranks, String[] suits, int[] values) throws FileNotFoundException {

        cards = new ArrayBag<Card>();

        for (String suit: suits)
            for (int rank = 0; rank < ranks.length; rank++) {
                Card card = new Card(ranks[rank], suit, values[rank]);
                cards.addNewEntry(card);
            }
        size = cards.getCurrentSize();
        shuffle(cards);
        createReplayFile(cards);
    }

    public static void shuffle(ArrayBag<Card> cards) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(cards.getCurrentSize());
            Card card = cards.get(index);
            cards.remove(cards.get(index));
            cards.addNewEntry(card);
        }
    }

    public void dealtCards() {
        if (cards.getCurrentSize() <= 9) {
            System.out.println("Cards left:" + cards.getCurrentSize());
            int n = 0;
            for (int i = cards.getCurrentSize(); i > 0; i--) {
                System.out.println(n + ": " + cards.get(i-1));
                n++;
            }
        }
        else {
            System.out.println("Cards left:" + cards.getCurrentSize());
            for (int i = 0; i < 9; i++) {
                System.out.println(i + ": " + cards.get(cards.getCurrentSize() - i - 1));
            }
        }
    }

    public void hint() throws IOException {
        boolean gameWinnable = false;
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                    int k = j + 1;
                    k++;
                    int currentCard1 = cards.get(cards.getCurrentSize() - i - 1).value();
                    int currentCard2 = cards.get(cards.getCurrentSize() - j - 1).value();
                    int currentCard3 = cards.get(cards.getCurrentSize() - k - 1).value();
                    String match = null;
                    if (currentCard1 + currentCard2 == 11) {
                        match = ("Hint: " + cards.get(cards.getCurrentSize() - i - 1) + " matches with " + cards.get(cards.getCurrentSize() - j - 1));
                        System.out.println(match);
                        gameWinnable = true;
                    }
                    if (currentCard1 + currentCard2 + currentCard3 == 221) {
                        match = ("Hint: " + cards.get(cards.getCurrentSize() - i - 1) + " matches with " + cards.get(cards.getCurrentSize() - j - 1) + " matches with " + cards.get(cards.getCurrentSize() - k - 1));
                        System.out.println(match);
                        gameWinnable = true;
                    }
                }
            }

        System.out.println("Press enter to continue: ");
        try{System.in.read();}
        catch(Exception e){}

        if (gameWinnable == false) {
            System.out.println("You have a stalemate!\n");
            System.out.println("Press enter to see a replay: ");
            try{System.in.read();}
            catch(Exception e){}
            repeat();
            }
        }

        public static void repeat() throws IOException {
        int i = 1;
        String fileName = "replayFile.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Move " + i + ": " + line + "\n");
                    i++;
                    try{System.in.read();}
                    catch(Exception e){}
                }
                System.out.println("Press enter to deal new deck: ");
                try{System.in.read();}
                catch(Exception e){}
                Game.run();
            }
        }

    public void checkWin(boolean runny) {

        if (cards.getCurrentSize() == 0) {
            System.out.println("\n\nGame won!");
            System.out.println();
            Deck.runny = false;
        }
        else {
            Deck.runny = true;
        }
    }

    public static void createReplayFile(ArrayBag<Card> cards) throws FileNotFoundException {
        String fileName = "replayFile.txt";
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("");
        writer.close();
    }

    public void choice2Card() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\nType in the number of the card: ");
        while (!scan.hasNextInt()) {
            System.out.print("\nType in the number of the card: ");
            scan.nextLine();
        }
        int numOfCard1 = scan.nextInt();
        Card cardChoice1 = cards.get(cards.getCurrentSize() - 1 - numOfCard1);
        System.out.println(cardChoice1);
        int cardChoice1Value = cardChoice1.value();

        System.out.print("\nType in the number of the card: ");
        while (!scan.hasNextInt()) {
            System.out.print("\nType in the number of the card: ");
            scan.nextLine();

        }
        int numOfCard2 = scan.nextInt();
        Card cardChoice2 = cards.get(cards.getCurrentSize() - 1 - numOfCard2);
        System.out.println(cardChoice2);
        int cardChoice2Value = cardChoice2.value();

        isLegal2Card(cardChoice1Value, cardChoice2Value, numOfCard1, numOfCard2);
    }

    public void takeReplayInput2Card(Card userChoice1, Card userChoice2) {
        try {
            String fileName = "replayFile.txt";
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(userChoice1 + " + ");
            pw.println(userChoice2);
            pw.close();
        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    public void isLegal2Card(int cardChoice1Value, int cardChoice2Value, int numOfCard1, int numOfCard2) {
        if (cardChoice1Value + cardChoice2Value == 11) {
            System.out.println("Match!");
            System.out.println("Press enter to continue: ");
            try{System.in.read();}
            catch(Exception e){}
            Card userChoice1 = cards.get(cards.getCurrentSize() - 1 - numOfCard1);
            Card userChoice2 = cards.get(cards.getCurrentSize() - 1 - numOfCard2);
            cards.remove(cards.get(cards.getCurrentSize() - 1 - numOfCard1));
            cards.remove(cards.get(cards.getCurrentSize() - numOfCard2));
            takeReplayInput2Card(userChoice1, userChoice2);
        }
        else {
            System.out.println("\nNo Match!");
            System.out.println("\nPress enter to try again: ");
            try{System.in.read();}
            catch(Exception e){}
        }
    }

    public void choice3Card() {

        Scanner scan = new Scanner(System.in);

        System.out.print("\nType in the number of the card: ");
        while (!scan.hasNextInt()) {
            System.out.print("\nType in the number of the card: ");
            scan.nextLine();
        }
        int numOfCard1 = scan.nextInt();
        Card cardChoice1 = cards.get(cards.getCurrentSize() - 1 - numOfCard1);
        System.out.println(cardChoice1);
        int cardChoice1Value = cardChoice1.value();

        System.out.print("\nType in the number of the card: ");
        while (!scan.hasNextInt()) {
            System.out.print("\nType in the number of the card: ");
            scan.nextLine();
        }
        int numOfCard2 = scan.nextInt();
        Card cardChoice2 = cards.get(cards.getCurrentSize() - 1 - numOfCard2);
        System.out.println(cardChoice2);
        int cardChoice2Value = cardChoice2.value();

        System.out.print("\nType in the number of the card: ");
        while (!scan.hasNextInt()) {
            System.out.print("\nType in the number of the card: ");
            scan.nextLine();
        }
        int numOfCard3 = scan.nextInt();
        Card cardChoice3 = cards.get(cards.getCurrentSize() - 1 - numOfCard3);
        System.out.println(cardChoice3);
        int cardChoice3Value = cardChoice3.value();

        isLegal3Card(cardChoice1Value, cardChoice2Value, cardChoice3Value, numOfCard1, numOfCard2, numOfCard3);
    }

    public void takeReplayInput3Card(Card userChoice1, Card userChoice2, Card userChoice3) {
        try {
            String fileName = "replayFile.txt";
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(userChoice1 + " + ");
            pw.print(userChoice2 + " + ");
            pw.println(userChoice3);
            pw.close();
        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    public void isLegal3Card(int cardChoice1Value, int cardChoice2Value, int cardChoice3Value, int numOfCard1, int numOfCard2, int numOfCard3) {
        if (cardChoice1Value + cardChoice2Value + cardChoice3Value == 221) {
            System.out.println("Match!");
            System.out.println("Press enter to continue: ");
            try{System.in.read();}
            catch(Exception e){}
            Card userChoice1 = cards.get(cards.getCurrentSize() - 1 - numOfCard1);
            cards.remove(cards.get(cards.getCurrentSize() - 1 - numOfCard1));
            Card userChoice2 = cards.get(cards.getCurrentSize() - numOfCard2);
            cards.remove(cards.get(cards.getCurrentSize() - numOfCard2));
            Card userChoice3 = cards.get(cards.getCurrentSize() + 1 - numOfCard3);
            cards.remove(cards.get(cards.getCurrentSize() + 1 - numOfCard3));
            takeReplayInput3Card(userChoice1, userChoice2, userChoice3);
        }
        else {
            System.out.println("\nNo Match!");
            System.out.println("\nPress enter to try again: ");
            try{System.in.read();}
            catch(Exception e){}
        }
    }

    @Override
    public String toString() {
        String dealing = "Size of deck = " + size;
        dealing = dealing + "\nDealt cards:";
        dealing = dealing + "\n";
        return dealing;
    }
}