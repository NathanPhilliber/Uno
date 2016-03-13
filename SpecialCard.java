
/**
 * Creates a special card with a color and a type, has methods to get and set
 * the type of special. Also has methods to make a deep copy, a toString method
 * that prints the type of special card, a getImage to get a png file of the
 * card, and equal method and a isWild checker.
 *
 * @author Team Uno
 * @version Program 7
 */
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;

public class SpecialCard extends Card {

    private int type;

    /**
     * Creates a special card with a specific color and type.
     *
     * @param color An int(0-4) representing the color of the card.
     * @param type An int(0-4) representnig the type of special.
     */
    public SpecialCard(int color, int type) {
        super(color);
        setType(type);
    }

    /**
     * Returns an int representing the type of special card.
     *
     * @return An int representing the type of special card.
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of special to a specific type.
     *
     * @param type An int(0-4) representing the type of special.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Makes a deep copy of a special card.
     *
     * @return A new special card with the same parameters.
     */
    public Card copy() {
        return new SpecialCard(getColor(), getType());
    }

    /**
     * Prints the type of special card this card is.
     *
     * @return A String of the color and type of the card.
     */
    public String toString() {

        String c = "";

        switch (getType()) {
            case 0:
                c = "SKIP";
                break;
            case 1:
                c = "REVERSE";
                break;
            case 2:
                c = "PLUS TWO";
                break;
            case 3:
                c = "WILD PLUS FOUR";
                break;
            case 4:
                c = "WILD";
                break;
            default:
                c = "NONE";
                break;
        }
        return super.toString() + ", " + c;
    }

    /**
     * Finds which png file corresponds to the specific special card.0
     *
     * @return The png file of that card.
     */
    public ImageIcon getImage() {

        if (getType() == Card.WILD) {
            return new ImageIcon("graphics/wild.png");
        }
        if (getType() == Card.WILDPLUSFOUR) {
            return new ImageIcon("graphics/wildplusfour.png");
        }

        String fileName = "graphics/";

        switch (getColor()) {
            case 0:
                fileName += "red";
                break;
            case 1:
                fileName += "yellow";
                break;
            case 2:
                fileName += "green";
                break;
            case 3:
                fileName += "blue";
                break;
            default:
                throw new NoSuchElementException();
        }

        fileName += "-";

        switch (getType()) {
            case 0:
                fileName += "skip";
                break;
            case 1:
                fileName += "reverse";
                break;
            case 2:
                fileName += "plustwo";
                break;
        }

        return new ImageIcon(fileName + ".png");

    }

    /**
     * checks whether this card is equal to another object.
     *
     * @param other The object being compared to the card.
     * @return A boolean true if equal false if not.
     */
    public boolean equals(Object other) {
        if (super.equals(other)) {
            SpecialCard cardN = (SpecialCard) other;
            if (cardN.getType() == getType()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether this special card is a wild card.
     *
     * @return A boolean true if wild false if not.
     */
    public boolean isWild() {
        if (getType() == Card.WILD || getType() == Card.WILDPLUSFOUR) {
            return true;
        }
        return false;
    }

}
