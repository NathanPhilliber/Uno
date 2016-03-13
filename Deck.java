/**
 * The deck class keeps track of card distributions and manages random card drawing.
 * @author Team Uno
 * @version Program 7
 */
import java.util.Random;

public class Deck {
   
   private static Card[] templateDeck = new Card[104];
   private static Random rand = new Random();
   
   /**
    * Create a new deck object.
    */
   public Deck(){
      if(templateDeck[0] == null){
         initializeDeck();
      }
   }

   private static void initializeDeck(){
      
      int current = 0;
      for(int k = 0; k < 2; k++){
         for(int j = 0; j < 4; j++){
            for(int i = 0; i < 9; i++){
               templateDeck[current] = new NumberedCard(j, i+1);
               current++;
            }
         }
      }
      for(int k = 0; k < 2; k++){
         for(int j = 0; j < 4; j++){
            for(int i = 0; i < 3; i++){
               templateDeck[current] = new SpecialCard(j, i);
               current++;
            }
         }
      }
      
      for(int i = 0; i < 4; i++){
         templateDeck[current] = new SpecialCard(Card.NONE, Card.WILD);
         current++;
      }
      for(int i = 0; i < 4; i++){
         templateDeck[current] = new SpecialCard(Card.NONE, Card.WILDPLUSFOUR);
         current++;
      }
      
   }
   
   /**
    * Return a card from the deck, does not effect deck.
    */
   
   public static Card drawCard(){
      return templateDeck[rand.nextInt(104)].copy();
   }
}
