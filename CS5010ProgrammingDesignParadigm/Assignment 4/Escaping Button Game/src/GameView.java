import javax.swing.*;

/**
 * The GameView class is responsible for creating and managing the GUI for the Escaping Button Game.
 * It includes components such as the main frame, button, and labels to display the game status,
 * success clicks, total clicks, and elapsed time.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-28
 */
public class GameView {
    private final JFrame frame;
    private final JButton button;
    private final JLabel statusLabel;
    private final JLabel scoreLabel;
    private final JLabel clickCountLabel;
    private final JLabel timerLabel;

    /**
     * Constructs a new GameView, initializing the game window and all its GUI components.
     */
    public GameView() {
        // Initialize main frame
        frame = new JFrame("Escaping Button Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Create the Exit button
        button = new JButton("Exit");
        button.setBounds(250, 250, 100, 50);
        frame.add(button);

        // Create status label
        statusLabel = new JLabel("Ready to start!");
        statusLabel.setBounds(10, 10, 400, 20);
        frame.add(statusLabel);

        // Create success clicks label
        scoreLabel = new JLabel("Successful Clicks: 0");
        scoreLabel.setBounds(10, 40, 200, 20);
        frame.add(scoreLabel);

        // Create click count label
        clickCountLabel = new JLabel("Total Clicks: 0");
        clickCountLabel.setBounds(10, 70, 200, 20);
        frame.add(clickCountLabel);

        // Create timer label
        timerLabel = new JLabel("Elapsed Time: 0 ms");
        timerLabel.setBounds(10, 100, 200, 20);
        frame.add(timerLabel);
    }

    /**
     * Makes the game frame visible to the user.
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Gets the main button in the game.
     *
     * @return The button object.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Gets the main game frame.
     *
     * @return The frame object.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Updates the status message displayed on the status label.
     *
     * @param message The new status message to display.
     */
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    /**
     * Updates the success clicks display.
     *
     * @param score The updated success clicks
     */
    public void updateScore(int score) {
        scoreLabel.setText("Successful Clicks: " + score);
    }

    /**
     * Updates the total clicks count display with the given number of clicks.
     *
     * @param clicks The updated total number of clicks.
     */
    public void updateClickCount(int clicks) {
        clickCountLabel.setText("Total Clicks: " + clicks);
    }

    /**
     * Updates the timer display with the given elapsed time.
     *
     * @param time The elapsed time in milliseconds.
     */
    public void updateTimer(long time) {
        timerLabel.setText("Elapsed Time: " + time + " ms");
    }

    /**
     * Gets the width of the game frame.
     *
     * @return The width of the frame in pixels.
     */
    public int getFrameWidth() {
        return frame.getWidth();
    }

    /**
     * Gets the height of the game frame.
     *
     * @return The height of the frame in pixels.
     */
    public int getFrameHeight() {
        return frame.getHeight();
    }
}