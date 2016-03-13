/**
 * Whenever a new card is created this class is used to set the display in the gui.
 * @author Team Uno
 * @version Program 7
 */
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardDisplay extends JPanel {

   private static final long serialVersionUID = 1L;

   /**
    * Adds a new card and play button to the gui interface.
    * 
    * @param card
    *           The card being added to the gui.
    * @param index
    *           The card's index or id.
    * @param button
    *           The play button for this card.
    */
   public CardDisplay(Card card, JButton button) {

      setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      JLabel cardLabel = new JLabel(new ImageIcon(((card.getImage()).getImage()).getScaledInstance(239 / 2, 361 / 2, java.awt.Image.SCALE_SMOOTH)));
      add(cardLabel);
      add(button);
   }

}
