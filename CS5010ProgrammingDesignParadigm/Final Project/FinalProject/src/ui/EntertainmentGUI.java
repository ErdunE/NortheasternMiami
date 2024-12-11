package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import log.LogHelper;

import java.util.logging.Logger;

public class EntertainmentGUI extends Application {

    private static final Logger logger = LogHelper.getLogger(EntertainmentGUI.class);

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