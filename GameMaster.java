/**
 * Contains the main method to start the game.
 * @author Team Uno
 * @version Program 7
 */
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameMaster {

   private static ArrayList<Player> players;
   private static int numPlayers;
   private static int numHumans;
   private static int currentTurn;
   private static boolean reverse = false;
   private static Card topCard;
   private static ArrayList<GameWindow> windows = new ArrayList<GameWindow>();
   private static ArrayList<ComputerPlayer> computerPlayers = new ArrayList<ComputerPlayer>();

   /**
    * Return GameWindow at value.
    * 
    * @param index
    *           Which GameWindow to return.
    */ 
   public static GameWindow getWindow(int index) {
      return windows.get(index);
   }

   /**
    * Return number of GameWindow.
    * 
    * @return number of GameWindows.
    */
   public static int getWindowsSize() {
      return windows.size();
   }
   
   /**
    * Show message in all GameWindows
    * @param msg a String to display
    */
   public static void broadcast(String msg){
      for(int i = 0; i < windows.size(); i++){
         windows.get(i).writeMessage(msg);
      }
   }

   /**
    * Get the top card of the stack.
    * 
    * @return top card of the deck, type Card.
    */
   public static Card getTopCard() {
      return topCard;
   }

   /**
    * Set the top card of the deck.
    * 
    * @param topCard
    *           the card to set the top to.
    */
   public static void setTopCard(Card topCard) {
      GameMaster.topCard = topCard;
   }

   /**
    * Find out if the turns are reversed.
    * 
    * @return a boolean, true if the turns are reversed.
    */
   public static boolean getReverse() {
      return reverse;
   }

   /**
    * Reverse the turns
    */
   public static void reverse() {
      reverse = !reverse;
   }

   /**
    * Add a number of turns to the current one.
    * 
    * @param add
    *           the amount of turns to add.
    */
   public static void addCurrentTurn(int add) {
      currentTurn += add;
   }

   /**
    * Set the current turn count.
    * 
    * @param set
    *           The value to set the current turn to.
    */
   public static void setCurrentTurn(int set) {
      currentTurn = set;
   }

   /**
    * Get the current turn.
    * 
    * @return an int representing the current turn.
    */
   public static int getCurrentTurn() {
      return currentTurn;
   }

   /**
    * Get the number of total players.
    * 
    * @return an int representing the total number of players.
    */
   public static int getNumPlayers() {
      return numPlayers;
   }

   /**
    * Get the number of total human players.
    * 
    * @return an int representing the total number of human players.
    */
   public static int getNumHumans() {
      return numHumans;
   }

   /**
    * End the game.
    */
   public static void gameover() {
      GameMaster.setCurrentTurn(-10);
      for(int i = 0; i < GameMaster.getWindowsSize(); i++){
         GameMaster.getWindow(i).removeAllPanels();
      }

   }

   /**
    * Pass the top play card to all other windows.
    */
   public static void updateAllTopCards() {
      for(int i = 0; i < GameMaster.getWindowsSize(); i++){
         GameMaster.getWindow(i).updateTopCard();
      }
   }

   /**
    * Gets the total number of humans in the game.
    * 
    * @return An int representing the total number of human players.
    */
   public static int numberHumanPlayers() {
      return GameMaster.getNumHumans();
   }

   /**
    * Updates all players hands on the gui.
    */
   public static void updateAllPlayerHands() {
      if(GameMaster.getNumHumans() != 0){
         for(int r = 0; r < GameMaster.getWindowsSize(); r++){
            GameMaster.getWindow(r).updateDisplayHand(GameMaster.getWindow(r).getPlayer().getHand());
         }
      }
   }

   /**
    * Adds to the turns played in this game to see which player needs to go.
    */
   public static void advanceTurns() {
      if(GameMaster.getReverse()){
         GameMaster.addCurrentTurn(-1);
      }
      else{
         GameMaster.addCurrentTurn(1);
      }

      GameMaster.checkTurns();
   }

   /**
    * Used to pass multiple turns if need to skip a player.
    * 
    * @param number
    *           The number of turns skipped.
    */
   public static void advanceTurns(int number) {
      if(GameMaster.getReverse()){
         GameMaster.addCurrentTurn(-number);
      }
      else{
         GameMaster.addCurrentTurn(number);
      }

      GameMaster.checkTurns();
   }

   /**
    * Finds the player at this specific index.
    * 
    * @param index
    *           The index of which your player is at.
    * @return That player at that specific index.
    */
   public static Player getPlayer(int index) {

      if(index >= GameMaster.getNumPlayers()){
         if(numHumans == 0){
            return getComputerPlayer(0);
         }
         return GameMaster.getWindow(0).getPlayer();
      }
      if(index < 0){
         if(GameMaster.getNumHumans() == GameMaster.getNumPlayers()){
            return GameMaster.getWindow(GameMaster.getWindowsSize() - 1).getPlayer();
         }
         return GameMaster.getComputerPlayer(GameMaster.getComputerPlayerSize() - 1);
      }

      if(index < GameMaster.getNumHumans()){
         return GameMaster.getWindow(index).getPlayer();
      }
      else{
         return GameMaster.getComputerPlayer(index - GameMaster.getNumHumans());
      }
   }

   /**
    * Checks which player needs to play at the current turn.
    */
   public static void checkTurns() {
      if(GameMaster.getReverse()){
         if(GameMaster.getCurrentTurn() < 0){
            if(GameMaster.getNumHumans() == GameMaster.getNumPlayers()){
               GameMaster.setCurrentTurn(GameMaster.getWindowsSize() - 1);
            }
            else{
               GameMaster.setCurrentTurn(GameMaster.getNumPlayers() - 1);
            }
         }

         if(GameMaster.getCurrentTurn() >= GameMaster.getNumHumans()){
            GameMaster.getComputerPlayer(GameMaster.getCurrentTurn() - GameMaster.getNumHumans()).takeTurn();
         }
         else{
            GameMaster.getWindow(GameMaster.getCurrentTurn()).beginTurn();
         }
      }
      else{
         if(GameMaster.getCurrentTurn() >= GameMaster.getNumPlayers()){
            GameMaster.setCurrentTurn(GameMaster.getCurrentTurn() - GameMaster.getNumPlayers());
         }

         if(GameMaster.getCurrentTurn() >= GameMaster.getNumHumans()){
            GameMaster.getComputerPlayer(GameMaster.getCurrentTurn() - GameMaster.getNumHumans()).takeTurn();
         }
         else{
            GameMaster.getWindow(GameMaster.getCurrentTurn()).beginTurn();
         }
      }

   }

   /**
    * Get the number of computers playing.
    * 
    * @return an int representing the number of computer players.
    */
   public static int getComputerPlayerSize() {
      return computerPlayers.size();
   }

   /**
    * Get the computer player at the current index.
    * 
    * @param index
    *           Which computer player to return.
    * @return a ComputerPlayer at the provided index.
    */
   public static ComputerPlayer getComputerPlayer(int index) {
      return computerPlayers.get(index);
   }

   /**
    * Adds another computer player to the total computer player.
    * 
    * @param player
    *           The computer player being added.
    */
   public static void addComputerPlayer(ComputerPlayer player) {
      computerPlayers.add(player);
   }

   /**
    * Begin the game, prompt user for number of players.
    * 
    * @return false if player cancels, true if player enters all information correctly.
    */
   public static boolean tryBeginGame() {

      players = new ArrayList<Player>();
      boolean go = false;
      do{
         String numPlayersString = (String) JOptionPane.showInputDialog(null, "How many players total?", "UNO - Pregame Settings", JOptionPane.PLAIN_MESSAGE, null, null, null);
         if(numPlayersString == null){
            return false;
         }
         try{
            numPlayers = Integer.parseInt(numPlayersString);
            go = true;
         } catch (NumberFormatException e){

         }
      } while(go == false || numPlayers < 2);

      go = false;

      do{
         String numHumanString = (String) JOptionPane.showInputDialog(null, "How many of the players are human?", "UNO - Pregame Settings", JOptionPane.PLAIN_MESSAGE, null, null, null);
         if(numHumanString == null){
            return false;
         }
         try{
            numHumans = Integer.parseInt(numHumanString);
            go = true;

            if(numHumans > numPlayers){
               go = false;
            }
         } catch (NumberFormatException e){

         }
      } while(go == false || numHumans < 0);

      return true;
   }

   /**
    * Create the player objects and add them to the lists.
    */
   public static void setupGame() {
      @SuppressWarnings("unused")
      Deck deck = new Deck();

      Card top;
      do{
         GameMaster.setTopCard(top = Deck.drawCard());
      } while(top.isWild());

      for(int i = 0; i < numPlayers; i++){
         if(i < numHumans){
            players.add(new HumanPlayer(i));
            windows.add(new GameWindow(numHumans, i, numPlayers, players.get(players.size() - 1)));
         }
         else{
            players.add(new ComputerPlayer(i));
            computerPlayers.add((ComputerPlayer) players.get(players.size() - 1));

         }
      }

      if(numHumans == 0){
         windows.add(new GameWindow());
      }
   }

   public static void main(String[] args) {

      if(tryBeginGame() == false){
         return;
      }

      setupGame();

   }

}