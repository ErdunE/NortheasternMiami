import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Piece class represents an individual square of chocolate in the Chomp game.
 * It maintains the state information for the square (chocolate, empty, or poison) and its coordinates.
 * According to the provided class diagram, this class belongs to the Model package and holds
 * information pertinent to the chocolate piece objects.
 *
 * @author Erdun E
 * @since 2024-11-13
 * @version 1.3
 */
public class Piece {
    private JPanel panel;
    private JButton button;
    private ImageIcon chocolate, empty, poison; // Add icon poison square for clarity
    private boolean eaten = false;
    private int row, col;
    private Board board; // Refer to Board instance and used to update game state and trigger actions when this piece is clicked.

    /**
     * Constructor for the Piece class, initializes the piece with its coordinates and board reference.
     * Sets the icon based on whether the piece is poison or chocolate.
     *
     * @param row the row coordinate of the piece
     * @param col the column coordinate of the piece
     * @param board the board that contains this piece
     */
    public Piece(int row, int col, Board board) {
        // Reason for modification: Added reference to the Board and additional poison icon setting for clarity.
        this.row = row;
        this.col = col;
        this.board = board;

        panel = new JPanel();
        button = new JButton();

        chocolate = new ImageIcon(getClass().getResource("/resources/chocolate.jpg"));
        empty = new ImageIcon(getClass().getResource("/resources/empty.jpg"));
        poison = new ImageIcon(getClass().getResource("/resources/poison.jpg"));

        if (row == 0 && col == 0) {
            button.setIcon(poison); // Sets the poison icon for the upper left square
        } else {
            button.setIcon(chocolate);
        }

        button.addActionListener(new ButtonClickListener()); // Observes button clicks
        panel.add(button);
    }

    /**
     * Returns the JPanel containing the button.
     * Expected: JPanel as per class diagram.
     * Adherence: This method aligns with the design, providing access to the piece’s UI component.
     *
     * @return JPanel the container panel for the button
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Updates the piece's state to eaten, disabling the button and changing its icon.
     * Expected: Method to update state after chomped as per class diagram.
     * Adherence: This method follows the design, switching the piece's appearance and state.
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
     */
    private class ButtonClickListener implements ActionListener {
        // Reason for modification: Implemented using ActionListener instead of direct method for better GUI integration.
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