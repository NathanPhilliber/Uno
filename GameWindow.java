/**
 * Used set up and run the gui window for a game of uno.
 * @author Team Uno
 * @version Program 7
 */
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GameWindow extends JFrame implements ActionListener {

   private static final long serialVersionUID = 1L;
   private JPanel everythingPanel = new JPanel();
   private JPanel gameAreaPanel = new JPanel();
   private JPanel handPanel = new JPanel();
   private JPanel optionsPanel = new JPanel();

   JButton hideButton = new JButton("Hide Hand");
   JButton drawButton = new JButton("Draw Card");
   JButton playButton = new JButton("Play Card");
   JButton unoButton = new JButton("Uno!");
   JButton attackButton = new JButton("They didn't say Uno!");
   
   private JTextArea textArea;
   private JScrollPane scroll;

   JLabel yourTurn = new JLabel(new ImageIcon("graphics/YouTurn.png"), SwingConstants.CENTER);

   private boolean hideCards = true;
   private Player player;

   private ArrayList<JButton> playButtons = new ArrayList<JButton>();

   /**
    * Create a GameWindow that's not associated with an player variables
    */
   public GameWindow(){
      updateTopCard();
      
      textArea = new JTextArea();
      textArea.setSize(100, 50);
      textArea.setLineWrap(true);
      textArea.setEditable(false);
      textArea.setVisible(true);
      scroll = new JScrollPane(textArea);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      
      everythingPanel.setLayout(new BoxLayout(everythingPanel, BoxLayout.PAGE_AXIS));
      everythingPanel.add(optionsPanel);
      everythingPanel.add(handPanel);
      everythingPanel.add(gameAreaPanel);
      everythingPanel.add(scroll);
      add(everythingPanel);
      
      setSize(1476, 830);
      setTitle("UNO");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(true);
      setVisible(true);
      
      GameMaster.getComputerPlayer(0).takeTurn();
      

   }
   /**
    * Constructs a game window for a game of uno.
    * 
    * @param numHumans
    *           A number representing the number of humans in a game.
    * @param myTurn
    *           A number representing the number of turns in a game.
    * @param totalPlayers
    *           A number representing the total number of humans and computer players.
    * @param player
    */
   public GameWindow(int numHumans, int myTurn, int totalPlayers, Player player) {

      this.player = player;

      updateDisplayHand(getPlayer().getHand());
      updateTopCard();
      
      textArea = new JTextArea();
      textArea.setSize(100, 50);
      textArea.setLineWrap(true);
      textArea.setEditable(false);
      textArea.setVisible(true);
      scroll = new JScrollPane(textArea);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      
      this.drawButton.addActionListener(this);
      this.hideButton.addActionListener(this);
      this.playButton.addActionListener(this);
      this.unoButton.addActionListener(this);
      this.attackButton.addActionListener(this);

      optionsPanel.add(hideButton);
      optionsPanel.add(drawButton);

      everythingPanel.setLayout(new BoxLayout(everythingPanel, BoxLayout.PAGE_AXIS));
      everythingPanel.add(optionsPanel);
      everythingPanel.add(handPanel);
      everythingPanel.add(gameAreaPanel);
      everythingPanel.add(scroll);
      add(everythingPanel);

      setSize(1476, 830);
      setTitle("UNO");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(true);
      setVisible(true);

      if(getPlayer().myTurn() == 0){
         beginTurn();
      }
   }
   
   /**
    * Display a String on the text area
    * @param msg a String to display
    */
   public void writeMessage(String msg){
      textArea.append("\n" + msg);
      scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
         public void adjustmentValueChanged(AdjustmentEvent e) {
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
         }
      });
   }
   
   
   /**
    * If someone else calls uno and another has one card this method will give the player with one card another card.
    */
   public void attack() {
      if(getPlayer().isSafe() == false){
         getPlayer().getHand().drawCards(1);
         optionsPanel.remove(unoButton);
         validate();
         repaint();
      }
   }
   
   /**
    * Check all player's hand to see if anyone has uno.
    */
   public void checkUno() {
      if(getPlayer().getHand().size() == 1){
         optionsPanel.add(unoButton);
         giveAttackOption();
         getPlayer().setSafe(false);
         validate();
         repaint();
      }
      if(getPlayer().getHand().size() > 1){
         getPlayer().setSafe(true);
         optionsPanel.remove(unoButton);
         handPanel.validate();
         handPanel.repaint();
         validate();
         repaint();
      }
   }

   /**
    * Used when a player plays a card, will check if valid card and finally replace the top card.
    */
   public void updateTopCard() {
      gameAreaPanel.removeAll();
      gameAreaPanel.add(new JLabel(new ImageIcon(((GameMaster.getTopCard().getImage()).getImage()).getScaledInstance(239 / 2, 361 / 2, java.awt.Image.SCALE_SMOOTH))));
      validate();
      repaint();
   }

   /**
    * If player has one card add uno option.
    */
   public void addAttackButton() {
      optionsPanel.add(attackButton);
      validate();
      repaint();
   }

   /**
    * if a player has uno give option for others to call uno.
    */
   public void giveAttackOption() {
      for(int i = 0; i < GameMaster.getWindowsSize(); i++){
         if(GameMaster.getWindow(i).getPlayer() != getPlayer()){
            GameMaster.getWindow(i).addAttackButton();
         }
      }
   }

   /**
    * If player no longer has uno stop giving them the option of calling uno.
    */
   public void removeAttack() {
      optionsPanel.remove(attackButton);
      validate();
      repaint();
   }

   /**
    * If player no longer has uno stop giving other's option to call uno.
    */
   public static void removeAttackButton() {
      for(int i = 0; i < GameMaster.getWindowsSize(); i++){

         GameMaster.getWindow(i).removeAttack();

      }
   }

   /**
    * Used to get the current player.
    * 
    * @return The current player.
    */
   public Player getPlayer() {
      return player;
   }

   /**
    * Changes the players hand whenever a card is played.
    * 
    * @param hand
    *           The hand of which the card came from and needs to be updated.
    */
   public void updateDisplayHand(Hand hand) {
      handPanel.removeAll();
      playButtons.clear();
      for(int i = 0; i < hand.size(); i++){
         if(hideCards){
            ImageIcon icon = new ImageIcon("graphics/hidden.png");
            Image img = icon.getImage().getScaledInstance(239 / 2, 361 / 2, java.awt.Image.SCALE_SMOOTH);
            handPanel.add(new JLabel(new ImageIcon(img)));
            hideButton.setText("Show Cards");
         }
         else{
            JButton tempButton = new JButton("Play Card");
            tempButton.addActionListener(this);
            playButtons.add(tempButton);

            CardDisplay cardD = new CardDisplay(hand.getCard(i), tempButton);
            handPanel.add(cardD);

            hideButton.setText("Hide Cards");

         }

      }
      validate();
      repaint();
   }

   /**
    * Add the graphic to the screen and repaint
    */
   public void beginTurn() {
      gameAreaPanel.add(yourTurn);
      validate();
      repaint();
   }

   /**
    * Removes everything on the game board and adds a game over graphic.
    */
   public void removeAllPanels() {
      everythingPanel.removeAll();

      JLabel gameoverLabel = new JLabel(new ImageIcon("graphics/gameover.png"), SwingConstants.CENTER);
      everythingPanel.add(gameoverLabel);
      everythingPanel.add(scroll);
      
      if(getPlayer() != null){
         if(getPlayer().getHand().size() == 0){
            writeMessage("\n\nYou Won!");
         }
         else{
            writeMessage("\n\nYou Lost!");
         }
      }
      validate();
      repaint();
   }

   /**
    * Checks all the actions in everyone's window and will preform an action on the gui if that action the player preforms is valid at that time.
    * 
    * @param event
    *           Any action a user preforms on the gui window.
    */
   public void actionPerformed(ActionEvent event) {

      checkUno();

      if(event.getSource() == attackButton){
         removeAttackButton();
         for(int i = 0; i < GameMaster.getWindowsSize(); i++){
            if(GameMaster.getWindow(i).getPlayer().isSafe() == false && GameMaster.getWindow(i).getPlayer() != getPlayer()){
               GameMaster.broadcast("[Player " + getPlayer().myTurn() + "] Player " + GameMaster.getWindow(i).getPlayer().myTurn() + " didn't say Uno!");
               GameMaster.getWindow(i).getPlayer().getHand().drawCards(1);
               GameMaster.getWindow(i).getPlayer().setSafe(true);
               GameMaster.getWindow(i).checkUno();
               GameMaster.getWindow(i).updateDisplayHand(GameMaster.getWindow(i).getPlayer().getHand());
            }
         }
      }

      if(event.getSource() == unoButton){
         getPlayer().setSafe(true);
         optionsPanel.remove(unoButton);
         GameMaster.broadcast("[Player " + getPlayer().myTurn() + "] Uno!");
         removeAttackButton();
         validate();
         repaint();
      }

      if(event.getSource() == hideButton){
         hideCards = !hideCards;
         updateDisplayHand(getPlayer().getHand());
      }
      if(event.getSource() == drawButton){
         if(getPlayer().myTurn() == GameMaster.getCurrentTurn()){
            getPlayer().getHand().addCard(Deck.drawCard());
            updateDisplayHand(getPlayer().getHand());
            GameMaster.broadcast("[Player " + getPlayer().myTurn() + "] Drawing Card");

            GameMaster.advanceTurns(1);
            gameAreaPanel.remove(yourTurn);

         }
      }

      for(int i = 0; i < playButtons.size(); i++){
         if(event.getSource() == playButtons.get(i)){
            if(getPlayer().myTurn() == GameMaster.getCurrentTurn()){

               Card selected = getPlayer().getHand().getCard(i);

               if(selected.isWild()){
                  Object[] options = { "RED", "GREEN", "YELLOW", "BLUE" };
                  String wildColor;
                  do{
                     wildColor = (String) JOptionPane.showInputDialog(null, "Which color to make?", "UNO - Pregame Settings", JOptionPane.PLAIN_MESSAGE, null, options, null);
                  } while(wildColor == null);
                  int color = 5;
                  if(wildColor.equals("RED")){
                     color = 0;
                  }
                  else if(wildColor.equals("YELLOW")){
                     color = 1;
                  }
                  else if(wildColor.equals("GREEN")){
                     color = 2;
                  }
                  else if(wildColor.equals("BLUE")){
                     color = 3;
                  }

                  SpecialCard tempCard = (SpecialCard) selected;
                  if(tempCard.getType() == Card.WILD){
                     GameMaster.setTopCard(new NumberedCard(color, 11 + color));
                  }
                  else{
                     GameMaster.setTopCard(new NumberedCard(color, 21 + color));
                     if(GameMaster.getReverse()){
                        GameMaster.getPlayer(GameMaster.getCurrentTurn() - 1).getHand().drawCards(4);
                     }
                     else{
                        GameMaster.getPlayer(GameMaster.getCurrentTurn() + 1).getHand().drawCards(4);
                     }

                     if(GameMaster.getPlayer(GameMaster.getCurrentTurn() + 1) instanceof HumanPlayer){
                        for(int r = 0; r < GameMaster.getWindowsSize(); r++){
                           GameMaster.getWindow(r).updateDisplayHand(GameMaster.getWindow(r).getPlayer().getHand());
                        }
                     }
                  }

               }
               int next = 1;
               if(Card.valid(selected, GameMaster.getTopCard())){
                  if(!selected.isWild()){
                     GameMaster.setTopCard(selected);

                     if(selected instanceof SpecialCard){

                        SpecialCard theCard = (SpecialCard) selected;

                        if(theCard.getType() == Card.SKIP){
                           next++;
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
                  }

                  getPlayer().getHand().removeCard(i);
                  updateDisplayHand(getPlayer().getHand());
                  GameMaster.updateAllTopCards();
                  checkUno();
                  gameAreaPanel.remove(yourTurn);
                  if(getPlayer().getHand().size() == 0){
                     GameMaster.broadcast("\n\n\n\n[Player " + getPlayer().myTurn() + "] WINS!");
                     
                     GameMaster.gameover();
                  }
                  else{
                     GameMaster.advanceTurns(next);
                  }
               }
            }
         }
      }
   }
}
