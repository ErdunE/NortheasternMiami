import javax.swing.*;

/**
 * The Controller class manages the core game logic for the Chomp game.
 * It holds the game loop, updates the player turns, and controls the game
 * end conditions.
 *
 * @author Erdun E
 * @since 2024-11-13
 * @version 1.3
 */
public class Controller {
    private byte player = 0;
    private Board board;

    /**
     * Initializes the game by creating the Board instance and setting up the GUI.
     */
    public void init() {
        // Reason for modification: `init()` was expected to contain a game loop
        // that continuously updates the game state. However, this implementation
        // is event-driven, which aligns with GUI applications where user actions
        // drive state changes.
        board = new Board(this);
        board.createBoard();
    }

    /**
     * Switches the current player by toggling the `player` variable between 0 and 1.
     * Updates the game view to display the current player's turn.
     */
    public void switchPlayer() {
        // Reason for Addition: Though not specified in the class diagram, this
        // method cleanly separates player switching logic and improves readability.
        player = (byte) (1 - player);
        board.updateMessage("Player " + (player + 1) + " Turn");
    }

    /**
     * Resets the player to Player 1 at the start of a new game.
     */
    public void resetPlayer() {
        // Reason for Addition: This was added to ensure the game always starts
        // with Player 1, contributing to consistent gameplay flow.
        player = 0;
    }

    /**
     * Ends the game by showing a dialog with options to start a new game or exit.
     */
    public void gameEnd() {
        int result = showGameOverDialog("Game Over! Player " + (player + 1) + " loses!");

        if (result == JOptionPane.YES_OPTION) {
            board.startNewGame();
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Displays a game over dialog with options for "New Game" and "OK".
     *
     * @param message the message to display in the dialog
     * @return an integer indicating the selected option
     */
    private int showGameOverDialog(String message) {
        // Reason for Addition: This helper method encapsulates dialog logic,
        // making `gameEnd()` more concise and readable.
        Object[] options = {"New Game", "OK"};
        return JOptionPane.showOptionDialog(
                null, message, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
        );
    }
}