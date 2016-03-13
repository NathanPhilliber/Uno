/**
 * Class that deal with numbered cards.
 *
 * @author Team Uno
 * @version Program 7
 */

import javax.swing.ImageIcon;

public class NumberedCard extends Card {

   private int number;

   /**
    * Creates a new numbered card with a color and a number.
    *
    * @param color
    *           An int(0-4) that represents the color of the card.
    * @param number
    *           An int that represents this cards number.
    */
   public NumberedCard(int color, int number) {
      super(color);
      setNumber(number);
   }

   /**
    * Gets the number of that numbered card.
    *
    * @return An int that represents the number of the card.
    */
   public int getNumber() {
      return number;
   }

   /**
    * Used to set the numbered cards number to a specific number.
    *
    * @param number
    *           The specific number the cards number is being replaced
    *           with.
    */
   public void setNumber(int number) {
      this.number = number;
   }

   /**
    * makes a deep copy of a numbered card.
    *
    * @return A new numbered card with the same parameters of the card.
    */
   public Card copy() {
      return new NumberedCard(getColor(), getNumber());
   }

   /**
    * Prints the elements of this numbered card.
    *
    * @return A string that states the cards color and number.
    */
   public String toString() {
      return super.toString() + ", " + getNumber();
   }

   /**
    * Checks if a numbered card is equal to another object.
    *
    * @param other
    *           The other object the card is being compared with.
    * @return A boolean true if equal and false if not.
    */
   public boolean equals(Object other) {
      if(super.equals(other)){
         NumberedCard cardN = (NumberedCard) other;
         if(cardN.getNumber() == getNumber()){
            return true;
         }
      }
      return false;
   }

   /**
    * Used to find the png file that responds to that specific numbered card.
    *
    * @return A png file with that numbered card's image.
    */
   public ImageIcon getImage() {

      String fileName = "graphics/";

      if(getNumber() > 10 && getNumber() < 20){

         fileName += "wild-";
      }
      if(getNumber() > 20 && getNumber() < 30){
         fileName += "wildplusfour-";
      }

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
      }

      if(getNumber() > 10){
         return new ImageIcon(fileName + ".png");
      }

      return new ImageIcon(fileName + "-" + getNumber() + ".png");

   }
}
