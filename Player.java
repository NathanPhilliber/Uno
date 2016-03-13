
/**
 * Creates a new player with a player id and a specific hand, has methods to get
 * and set the player's hand and can draw a new hand.
 *
 * @author Team Uno
 * @version Program 7
 */
public abstract class Player {

    private Hand hand;
    private int myTurn;
    private boolean safe = true;
    
    public boolean isSafe(){
       return safe;
    }
    
    public void setSafe(boolean safe){
       this.safe = safe;
    }

    /**
     * Creates a new player with a default hand size.
     *
     * @param num The player's id.
     */
    public Player(int myTurn) {
        this(new Hand(), myTurn);
    }

    /**
     * Creates a new player object with a specific hand.
     *
     * @param hand The hand the new player will receive.
     * @param num The player's id number.
     */
    public Player(Hand hand, int myTurn) {
        setHand(hand);
        this.myTurn = myTurn;
    }

    /**
     * Used to set the player's hand to a specific defined hand.
     *
     * @param hand The hand replacing the current hand.
     */
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    /**
     * Used to return the player's hand.
     *
     * @return The hand of this player.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * returns the player's id.
     *
     * @return An int representing which player this is.
     */
    public int myTurn() {
        return myTurn;
    }

    /**
     * Fills the hand with random card.
     *
     * @param numberOfCards The number of cards being drawn.
     */
    public void start(int numberOfCards) {
        hand.drawCards(numberOfCards);
    }

    /**
     * A method ran whenever it is this player's turn.
     */
    public abstract void takeTurn();
}
