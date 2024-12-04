import java.awt.event.*;
import java.util.Random;

/**
 * The GameController class manages the logic and events for the Escaping Button Game.
 * It handles user interactions, such as mouse movements and clicks, and updates the game state
 * by coordinating with the GameView and TimerUtils classes.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-28
 */
public class GameController {
    private final GameView view;
    private final TimerUtils timerUtils;
    private int successfulClicks = 0;
    private int totalClicks = 0;

    /**
     * Constructs a new GameController with the specified GameView.
     */
    public GameController(GameView view) {
        this.view = view;
        this.timerUtils = new TimerUtils();
        setupListeners();
    }

    /**
     * Sets up event listeners for mouse actions and button clicks.
     */
    private void setupListeners() {
        // Global mouse listener for counting clicks
        view.getFrame().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                totalClicks++;
                view.updateClickCount(totalClicks);
            }
        });

        // Mouse movement starts the timer
        view.getButton().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                moveButtonRandomly();
                view.updateStatus("Catch me if you can!");
            }
        });

        // Mouse movement within the frame starts the dynamic timer
        view.getFrame().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                timerUtils.startDynamicTimer(view::updateTimer); // 实时更新计时器
            }
        });

        view.getButton().addActionListener(e -> handleButtonClick());
    }

    /**
     * Handles the logic when the button is successfully clicked.
     * Increments the successful click count, stops the timer, updates the status, and resets the dynamic timer.
     */
    private void handleButtonClick() {
        successfulClicks++;
        totalClicks++;
        view.updateClickCount(totalClicks);
        long elapsedTime = timerUtils.stopTimer(); // Stop timer
        view.updateStatus("Caught! Time: " + elapsedTime + " ms");
        view.updateScore(successfulClicks); // Update score display
        timerUtils.resetDynamicTimer(); // Reset dynamic timer
    }

    /**
     * Moves the button to a random position within the bounds of the frame.
     * Ensures the button does not move outside the visible area.
     */
    private void moveButtonRandomly() {
        Random random = new Random();
        int frameWidth = view.getFrameWidth();
        int frameHeight = view.getFrameHeight();
        int buttonWidth = view.getButton().getWidth();
        int buttonHeight = view.getButton().getHeight();

        // Generate random coordinates for the button
        int newX = random.nextInt(frameWidth - buttonWidth);
        int newY = random.nextInt(frameHeight - buttonHeight);

        // Set the new position of the button
        view.getButton().setBounds(newX, newY, buttonWidth, buttonHeight);
    }

    /**
     * Starts the game by initializing the timer and displaying the game window.
     */
    public void startGame() {
        timerUtils.startTimer();
        view.show();
    }
}