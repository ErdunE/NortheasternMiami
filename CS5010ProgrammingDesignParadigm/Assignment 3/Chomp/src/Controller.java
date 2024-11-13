import javax.swing.*;

/**
 * The Controller class manages the core game logic for the Chomp game.
 * It holds the game loop, updates the player turns, and controls the game
 * end conditions.
 *
 * <p><b>Expected Design:</b> The Controller class was expected to hold a game loop
 * that updates the board view, controls player sequence, and manages the game
 * state based on player actions.</p>
 *
 * <p><b>Deviations and Extensions:</b> The design specified a continuous game loop
 * within `init()`, but this implementation instead relies on event-driven updates
 * from player actions, as is common in GUI applications. Additionally, `gameEnd()`
 * offers an option to restart the game instead of just exiting, providing a
 * user-friendly experience.</p>
 *
 * <p><b>Additional Methods:</b> `switchPlayer()` was added to handle player turns
 * more cleanly, and `showGameOverDialog()` encapsulates the logic for displaying
 * end-game options, enhancing readability.</p>
 *
 * @author Erdun E
 * @since 2024-11-12
 * @version 1.1
 */
public class Controller {
    private byte player = 0;
    private Board board;

    /**
     * Initializes the game by creating the Board instance and setting up the GUI.
     * <p>
     * <b>Expected Design:</b> `init()` was expected to contain a game loop
     * that continuously updates the game state. However, this implementation
     * is event-driven, which aligns with GUI applications where user actions
     * drive state changes.
     */
    public void init() {
        board = new Board(this);
        board.createBoard();
    }

    /**
     * Switches the current player by toggling the `player` variable between 0 and 1.
     * Updates the game view to display the current player's turn.
     * <p>
     * <b>Reason for Addition:</b> Though not specified in the class diagram,
     * this method cleanly separates player switching logic and improves readability.
     */
    public void switchPlayer() {
        player = (byte) (1 - player);
        board.updateMessage("Player " + (player + 1) + " Turn");
    }

    /**
     * Resets the player to Player 1 at the start of a new game.
     * <p>
     * <b>Reason for Addition:</b> This was added to ensure the game always starts
     * with Player 1, contributing to consistent gameplay flow.
     */
    public void resetPlayer() {
        player = 0;
    }

    /**
     * Ends the game by showing a dialog with options to start a new game or exit.
     * <p>
     * <b>Expected Design:</b> `gameEnd()` was expected to terminate the game
     * immediately when a player chomps the poisoned square. Here, it provides
     * a choice to either start a new game or exit, enhancing the user experience.
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
     * <p>
     * <b>Reason for Addition:</b> This helper method encapsulates dialog logic,
     * making `gameEnd()` more concise and readable.
     *
     * @param message the message to display in the dialog
     * @return an integer indicating the selected option
     */
    private int showGameOverDialog(String message) {
        Object[] options = {"New Game", "OK"};
        return JOptionPane.showOptionDialog(
                null, message, "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
        );
    }
}