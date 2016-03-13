
/**
 * An abstract class that defines methods used for both SpecialCards and
 * NumberedCards. This class contains the constructor Card(int color) and the
 * methods getColor(), setColor(int color), isWild(), equals(Object other),
 * valid(Card play, Card deck), toString(). This class defines methods copy(),
 * getImage() and isWild.
 *
 * @author Team Uno
 * @version Program 7
 */
import javax.swing.ImageIcon;

public abstract class Card implements BasicCard {

    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;
    public static final int NONE = 4;

    public static final int SKIP = 0;
    public static final int REVERSE = 1;
    public static final int PLUSTWO = 2;
    public static final int WILDPLUSFOUR = 3;
    public static final int WILD = 4;

    private int color;

    /**
     * Constructor that makes a card with an int(0-4) representing a color.
     *
     * @param color int(0-4) representing a color.
     */
    public Card(int color) {
        setColor(color);
    }

    /**
     * Used to make a deep copy of this card.
     *
     * @return Returns a new card with the same params.
     */
    public abstract Card copy();

    /**
     * Used to find the corresponding graphic for the card.
     *
     * @return A png file with the corresponding cards graphic.
     */
    public abstract ImageIcon getImage();

    /**
     * Returns a int corresponding to the color of the card.
     *
     * @return int corresponding to the color of the card.
     */
    public int getColor() {
        return color;
    }

    /**
     * Sets the color of the card with an int corresponding to the desired
     * color.
     *
     * @param color An int(0-4) of the desired color.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Checks whether this card is a wild card or not.
     *
     * @return Returns false if not wild true if wild.
     */
    public boolean isWild() {
        return false;
    }

    /**
     * Used to compare a card with another object. Will return false if they are
     * not equal and true if they are equal.
     *
     * @param other The object you want to compare the card with.
     * @return A boolean true if equal false if not.
     */
    public boolean equals(Object other) {
        if (other.getClass() == getClass()) {
            Card otherC = (Card) other;
            if (otherC.getColor() == getColor()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the card the player wants to play is valid to play or not. If
     * the card isn't the same number, color or isn't a wild card valid will
     * return false and the user will not be able to play this card.
     *
     * @param play The card the user selects by pressing play card.
     * @param deck The current top card on the play deck.
     * @return A boolean false if not playable and true if playable.
     */
    public static boolean valid(Card play, Card deck) {
        if (play instanceof SpecialCard) {
            SpecialCard playS = (SpecialCard) play;
            if (playS.getType() == Card.WILD || playS.getType() == Card.WILDPLUSFOUR) {
                return true;
            }
            if (deck instanceof SpecialCard) {
                SpecialCard deckS = (SpecialCard) deck;
                if (deckS.getType() == playS.getType()) {
                    return true;
                }
            }
        }
        if (play.getColor() == deck.getColor()) {
            return true;
        }

        if (play instanceof NumberedCard && deck instanceof NumberedCard) {
            NumberedCard playN = (NumberedCard) play;
            NumberedCard deckN = (NumberedCard) deck;
            if (playN.getNumber() == deckN.getNumber()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prints the color of the card using the getColor method.
     *
     * @return Returns a string in the format "Color: cardColor".
     */
    public String toString() {
        String c = "";

        switch (getColor()) {
            case 0:
                c = "RED";
                break;
            case 1:
                c = "YELLOW";
                break;
            case 2:
                c = "GREEN";
                break;
            case 3:
                c = "BLUE";
                break;
            default:
                c = "NONE";
                break;
        }

        return c;
    }
}
