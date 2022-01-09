import java.io.IOException;
import java.util.Scanner;

public class Game {

    private static boolean runGame = true;

    public static void run() throws IOException {

        String ranks[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String suits[] = {"Clubs", "Hearts", "Diamonds", "Spades"};
        int values[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 53, 71, 97};

        Deck D = new Deck(ranks, suits, values);

        Scanner scan = new Scanner(System.in); //print
            while (Deck.runny == true) {

                D.checkWin(Deck.runny); // ok
                if (Deck.runny == false) break;

                D.dealtCards();
                System.out.print("\n1: To choose between 2 cards\n2: To choose between 3 cards\n3: To get hints / check for stalemate\n\nType in choice: ");

                while (!scan.hasNextInt()) {
                    D.dealtCards();
                    System.out.println("\nInput is not a number.");
                    System.out.print("\n1: To choose between 2 cards\n2: To choose between 3 cards\n3: To get hints / check for stalemate\n\nType in choice: ");
                    scan.nextLine();
                }
                int choice = scan.nextInt();
                if (choice == 1) {
                    D.choice2Card();
                } else if (choice == 2) {
                    D.choice3Card();
                } else if (choice == 3) {
                    D.hint();
                } else {
                    System.out.println("Choose 1, 2 or 3");
                }
            }
        }

    public static void main(String[] args) throws IOException {

        while (runGame) {
            System.out.print("\n1: Play Game\n2: Exit\n\nType in choice: ");

            Scanner scan = new Scanner(System.in);
            while (!scan.hasNextInt()) {
                System.out.println("\nInput is not a number.");
                System.out.print("\n1: Play Game\n2: Exit\n\nType in choice: ");
                scan.nextLine();
            }
            int choice = scan.nextInt();

            if (choice == 1) {
                run();
            }
            else if (choice == 2) {
                runGame = false;
            }
        }
    }
}