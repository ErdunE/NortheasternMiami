import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A utility class for handling timers in the Escaping Button Game.
 * This class provides methods to manage a basic timer and a dynamic timer
 * for real-time updates, along with a callback interface for updating UI elements.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-28
 */
public class TimerUtils {
    private long startTime;
    private Timer dynamicTimer;

    /**
     * Starts the main timer by recording the current system time in milliseconds.
     */
    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Stops the main timer and calculates the elapsed time since the timer was started.
     *
     * @return The elapsed time in milliseconds.
     */
    public long stopTimer() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Starts a dynamic timer that periodically updates the elapsed time.
     * The dynamic timer runs at a fixed rate and invokes the provided callback
     * to update the elapsed time in the UI.
     */
    public void startDynamicTimer(TimerCallback callback) {
        // Avoid multiple timers
        if (dynamicTimer != null) {
            return;
        }
        // Reset and start the main timer
        startTimer();
        dynamicTimer = new Timer();
        dynamicTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startTime;
                SwingUtilities.invokeLater(() -> callback.onTimerUpdate(elapsedTime));
            }
        }, 0, 100); // Update every 100 ms
    }

    /**
     * Resets and stops the dynamic timer if it is currently running.
     */
    public void resetDynamicTimer() {
        if (dynamicTimer != null) {
            dynamicTimer.cancel();
            dynamicTimer = null;
        }
    }

    /**
     * A callback interface to handle real-time timer updates.
     * Implementations of this interface can define the behavior for updating UI components
     * or processing elapsed time data.
     */
    public interface TimerCallback {
        void onTimerUpdate(long time);
    }
}