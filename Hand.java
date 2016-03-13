   
/**
 * Creates a new player's hand and has methods to add, remove and get cards.
 * @author Team Uno
 * @version Program 7
 */
import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    /**
     * Creates a player's hand with an ArrayList at the default size.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Returns the card at a specific index in the player's hand.
     *
     * @param index The index of the card being returned.
     * @return The card at that specific index.
     * @throws IndexOutOfBoundsException The index is either smaller than 0 or
     * past the total cards in the deck.
     */
    public Card getCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException();
        }

        return hand.get(index);
    }

    /**
     * Removes the card at a specific index in the player's hand.
     *
     * @param index The index of the card being removed.
     * @throws IndexOutOfBoundsException The index is either smaller than 0 or
     * past the total cards in the deck.
     */
    public void removeCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException();
        }

        hand.remove(index);

    }

    /**
     * Counts how many cards of each color there are in the player's hand.
     *
     * @return An int representing the color with the most cards.
     */
    public int getMostColor() {
        int reds = 0;
        int yellows = 0;
        int greens = 0;
        int blues = 0;
        for (int i = 0; i < hand.size(); i++) {
            switch (hand.get(i).getColor()) {
                case Card.RED:
                    reds++;
                    break;
                case Card.YELLOW:
                    yellows++;
                    break;
                case Card.GREEN:
                    greens++;
                case Card.BLUE:
                    blues++;
                    break;
            }
        }
        int max = 0;
        max = Math.max(reds, yellows);
        max = Math.max(max, greens);
        max = Math.max(max, blues);

        if (max == reds) {
            return Card.RED;
        }
        if (max == yellows) {
            return Card.YELLOW;
        }
        if (max == greens) {
            return Card.GREEN;
        }
        return Card.BLUE;

    }

    /**
     * Removes a specific card from the player's hand.
     *
     * @param card The specific card being removed.
     */
    public void removeCard(Card card) {
        hand.remove(card);

    }

    /**
     * Draws a number of cards and calls addCard for each number.
     *
     * @param number The total number of cards you wish to draw.
     */
    public void drawCards(int number) {
        for (int i = 0; i < number; i++) {
            addCard(Deck.drawCard());
        }
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card being added to the hand.
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * returns the amount of cards in the hand.
     *
     * @return An int representing the total amount of card in the hand.
     */
    public int size() {
        return hand.size();
    }
}
