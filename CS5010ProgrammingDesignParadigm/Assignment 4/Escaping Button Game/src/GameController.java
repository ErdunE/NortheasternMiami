import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GameController {
    private final GameView view;
    private final TimerUtils timerUtils;
    private int successfulClicks = 0; // Successful button clicks
    private int totalClicks = 0;      // Total mouse clicks

    public GameController(GameView view) {
        this.view = view;
        this.timerUtils = new TimerUtils();
        setupListeners();
    }

    // Setup event listeners
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

        view.getFrame().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                timerUtils.startDynamicTimer(view::updateTimer); // 实时更新计时器
            }
        });

        view.getButton().addActionListener(e -> handleButtonClick());
    }

    // Handle button click logic
    private void handleButtonClick() {
        successfulClicks++;
        totalClicks++;
        view.updateClickCount(totalClicks);
        long elapsedTime = timerUtils.stopTimer(); // Stop timer
        view.updateStatus("Caught! Time: " + elapsedTime + " ms");
        view.updateScore(successfulClicks); // Update score display
        timerUtils.resetDynamicTimer(); // Reset dynamic timer
    }

    // Move the button to a random position
    private void moveButtonRandomly() {
        Random random = new Random();
        int frameWidth = view.getFrameWidth();
        int frameHeight = view.getFrameHeight();
        int buttonWidth = view.getButton().getWidth();
        int buttonHeight = view.getButton().getHeight();

        int newX = random.nextInt(frameWidth - buttonWidth);
        int newY = random.nextInt(frameHeight - buttonHeight);
        view.getButton().setBounds(newX, newY, buttonWidth, buttonHeight);
    }

    // Start the game
    public void startGame() {
        timerUtils.startTimer(); // Start initial timer
        view.show(); // Show the game window
    }
}