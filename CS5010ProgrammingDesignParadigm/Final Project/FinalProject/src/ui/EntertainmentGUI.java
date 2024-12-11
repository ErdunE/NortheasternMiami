package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import log.LogHelper;

import java.util.logging.Logger;

/**
 * The main GUI class for the Entertainment Recommendation System.
 * Initializes and displays the primary application window.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class EntertainmentGUI extends Application {

    private static final Logger logger = LogHelper.getLogger(EntertainmentGUI.class);

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * @param primaryStage The primary window of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Initializing Entertainment Recommendation System GUI...");

        primaryStage.setTitle("Entertainment Recommendation System");
        primaryStage.setMaximized(true);

        MainLayout mainLayout = new MainLayout(primaryStage);

        Scene scene = new Scene(mainLayout.getMainLayout(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        logger.info("Entertainment Recommendation System launched successfully.");
    }
}