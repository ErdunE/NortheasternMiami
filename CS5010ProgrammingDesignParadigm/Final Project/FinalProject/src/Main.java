import javafx.application.Application;
import log.LogHelper;
import ui.EntertainmentGUI;

import java.util.logging.Logger;

/**
 * The entry point for the Entertainment Recommendation System.
 * Initializes logging and launches the JavaFX application.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class Main {

    private static final Logger logger = LogHelper.getLogger(Main.class);

    /**
     * The main method to start the Entertainment Recommendation System.
     * Clears the log file, initializes logging, and launches the GUI.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        LogHelper.clearLogFile();
        logger.info("Entertainment Recommendation System is starting...");
        try {
            Application.launch(EntertainmentGUI.class, args);
        } catch (Exception e) {
            logger.severe("Entertainment Recommendation System failed to launch: " + e.getMessage());
            e.printStackTrace();
        } finally {
            logger.info("Entertainment Recommendation System has exited.");
        }
    }
}