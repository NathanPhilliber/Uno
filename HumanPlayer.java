/**
 * Creates a new human player with a player id and gets a new hand with 7 cards.
 * @author Team Uno
 * @version Program 7
 */
public class HumanPlayer extends Player{
   /**
    * Creates a new human player with a player id and gets a new hand with 7 cards.
    * @param num The player's id.
    */
   public HumanPlayer(int num){
      super(num);
      getHand().drawCards(7);
   }
   
   /*
    * Take the player's turn
    */
   public void takeTurn(){
      //Handled by GameWindow buttons
   }
   
}
