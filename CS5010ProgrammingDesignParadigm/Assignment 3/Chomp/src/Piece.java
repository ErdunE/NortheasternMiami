import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Piece class represents an individual square of chocolate in the Chomp game.
 * It maintains the state information for the square (chocolate, empty, or poison) and its coordinates.
 * According to the provided class diagram, this class belongs to the Model package and holds
 * information pertinent to the chocolate piece objects.
 *
 * <p><b>Expected Design:</b> As per the class diagram, the Piece class should include:
 * <ul>
 *   <li>Attributes: JPanel panel, JButton button, Image chocolate, Image empty,
 *       boolean eaten, int row, int col.</li>
 *   <li>Methods: onButtonClick() to handle button click events and call the board’s eat
 *       function with the piece’s coordinates.</li>
 * </ul>
 * </p>
 *
 * <p><b>Deviations and Extensions:</b> This implementation adheres closely to the provided design,
 * but includes the following modifications:
 * <ul>
 *   <li>Added poison icon (ImageIcon poison) to represent the poisoned square as a unique visual
 *       element. The class diagram didn’t specify a separate icon for the poison piece,
 *       but this addition enhances user experience by clearly identifying the poisoned square.</li>
 *   <li>The expected method `onButtonClick()` has been replaced with an inner class `ButtonClickListener`
 *       that implements `ActionListener` to handle button click events. This aligns with the Java Swing
 *       structure, where `ActionListener` is a common way to handle GUI events.</li>
 * </ul>
 * </p>
 *
 * @author Erdun E
 * @since 2024-11-12
 * @version 1.1
 */
public class Piece {
    private JPanel panel; // Container for the button.
    private JButton button; // Clickable JButton representing the piece.
    private ImageIcon chocolate, empty, poison; // Icon for a chocolate, empty and poison square (poison added for clarity)
    private boolean eaten = false; // Boolean indicating if the square is chomped.
    private int row, col; // Coordinates of the piece on the board.
    private Board board; // Refer to Board instance and used to update game state and trigger actions when this piece is clicked.

    /**
     * Constructor for the Piece class, initializes the piece with its coordinates and board reference.
     * Sets the icon based on whether the piece is poison or chocolate.
     *
     * <p><b>Expected:</b> Constructor with coordinates as per class diagram.
     * <p><b>Adherence:</b> This constructor aligns with the design, initializing piece state and visuals.
     * <p><b>Deviation:</b> Added reference to the Board and additional poison icon setting for clarity.</p>
     *
     * @param row the row coordinate of the piece
     * @param col the column coordinate of the piece
     * @param board the board that contains this piece
     */
    public Piece(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        this.board = board;

        panel = new JPanel();
        button = new JButton();

        chocolate = new ImageIcon(getClass().getResource("/resources/chocolate.jpg"));
        empty = new ImageIcon(getClass().getResource("/resources/empty.jpg"));
        poison = new ImageIcon(getClass().getResource("/resources/poison.jpg"));

        if (row == 0 && col == 0) {
            // Sets the poison icon for the upper left square
            button.setIcon(poison);
        } else {
            button.setIcon(chocolate);
        }

        // Observes button clicks
        button.addActionListener(new ButtonClickListener());
        panel.add(button);
    }

    /**
     * Returns the JPanel containing the button.
     * <p><b>Expected:</b> JPanel as per class diagram.
     * <p><b>Adherence:</b> This method aligns with the design, providing access to the piece’s UI component.
     *
     * @return JPanel the container panel for the button
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Updates the piece's state to eaten, disabling the button and changing its icon.
     * <p><b>Expected:</b> Method to update state after chomped as per class diagram.
     * <p><b>Adherence:</b> This method follows the design, switching the piece's appearance and state.
     *
     * @param eaten the new state of the piece
     */
    public void setEaten(boolean eaten) {
        this.eaten = eaten;
        button.setEnabled(false);
        button.setIcon(empty);
    }

    /**
     * Inner class that implements ActionListener to handle button clicks.
     * Calls the Board’s eat method with the piece’s coordinates when clicked.
     * <p><b>Expected:</b> Equivalent to onButtonClick() in class diagram.
     * <p><b>Deviation:</b> Implemented using ActionListener instead of direct method for better GUI integration.
     */
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!eaten) {
                board.eat(row, col);
                if (row == 0 && col == 0) {
                    board.gameOver();
                }
            }
        }
    }
}