/**
 * This class manages the computer players turns.
 * @author Team Uno
 * @version Program 7
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ComputerPlayer extends Player {

   private Timer timer;

   /**
    * Create a new computer player.
    * 
    * @param myTurn an int for the turn the player will go o
    */
   public ComputerPlayer(int myTurn) {
      super(myTurn);
      getHand().drawCards(7);
   }

   /**
    * Start the computer player's turn.
    */
   public void takeTurn() {
      int delay = 900;
      ActionListener taskPerformer = new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            go();
         }
      };
      timer = new Timer(delay, taskPerformer);
      timer.start();
   }

   private void go() {

      ArrayList<Card> options = new ArrayList<Card>();
      Card top = GameMaster.getTopCard();

      for(int i = 0; i < getHand().size(); i++){
         Card cur = getHand().getCard(i);
         if(cur.getColor() == top.getColor()){
            options.add(cur);
         }
         else if(cur instanceof NumberedCard && top instanceof NumberedCard){
            NumberedCard curN = (NumberedCard) cur;
            NumberedCard topN = (NumberedCard) top;
            if(topN.getNumber() == curN.getNumber()){
               options.add(cur);
            }
         }
         else if(cur instanceof SpecialCard && top instanceof SpecialCard){
            SpecialCard curN = (SpecialCard) cur;
            SpecialCard topN = (SpecialCard) top;
            if(topN.getType() == curN.getType()){
               options.add(cur);
            }
         }
         else if(cur.isWild()){
            options.add(cur);
         }
      }

      int skips = 1;
      if(options.size() > 0){
         Card maybe = null;
         Random rand = new Random();
         for(int i = 0; i < 5; i++){
            maybe = options.get(rand.nextInt(options.size()));
            if(!maybe.isWild()){
               break;
            }
         }

         if(maybe.isWild()){
            SpecialCard theCard = (SpecialCard) maybe;

            int commonColor = getHand().getMostColor();

            if(theCard.getType() == Card.WILD){
               GameMaster.setTopCard(new NumberedCard(commonColor, 11 + commonColor));
               GameMaster.broadcast("[Player " + myTurn() + "] Wild");
            }
            else{
               GameMaster.setTopCard(new NumberedCard(commonColor, 21 + commonColor));

               if(GameMaster.getReverse()){
                  GameMaster.getPlayer(GameMaster.getCurrentTurn() - 1).getHand().drawCards(4);
               }
               else{
                  GameMaster.getPlayer(GameMaster.getCurrentTurn() + 1).getHand().drawCards(4);
               }
               GameMaster.updateAllPlayerHands();
               GameMaster.broadcast("[Player " + myTurn() + "] Playing Wild Plus Four");
            }
         }
         else{

            if(maybe instanceof SpecialCard){
               SpecialCard theCard = (SpecialCard) maybe;

               if(theCard.getType() == Card.SKIP){
                  skips = 2;
               }
               if(theCard.getType() == Card.REVERSE){
                  GameMaster.reverse();
               }
               if(theCard.getType() == Card.PLUSTWO){
                  if(GameMaster.getReverse()){
                     GameMaster.getPlayer(GameMaster.getCurrentTurn() - 1).getHand().drawCards(2);
                  }
                  else{
                     GameMaster.getPlayer(GameMaster.getCurrentTurn() + 1).getHand().drawCards(2);
                  }

                  GameMaster.updateAllPlayerHands();
               }
            }

            GameMaster.setTopCard(maybe);
            GameMaster.broadcast("[Player " + myTurn() + "] Playing Card: " + maybe);
         }

         getHand().removeCard(maybe);
         GameMaster.updateAllTopCards();

      }
      else{
         GameMaster.broadcast("[Player " + myTurn() + "] Drawing Card");
         getHand().drawCards(1);
      }

      if(getHand().size() == 0){
         GameMaster.gameover();
         GameMaster.broadcast("\n\n\n\n[Player " + myTurn() + "] WINS!");
         timer.stop();
         return;
      }

      if(getHand().size() == 1){
         GameMaster.broadcast("[Player " + myTurn() + "] Uno!");
      }
      else{
         GameMaster.broadcast("[Player " + myTurn() + "] I have " + getHand().size() + " cards left.");
      }

      timer.stop();
      GameMaster.advanceTurns(skips);

   }
}
