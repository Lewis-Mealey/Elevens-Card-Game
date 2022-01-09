public class Card {

    private String rank;
    private String suit;
    private int value;

    public Card(String cardRank, String CardSuit, int CardValue) {
        rank = cardRank;
        suit = CardSuit;
        value = CardValue;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

}