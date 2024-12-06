package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntertainmentGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Entertainment Recommendation System");
        primaryStage.setMaximized(true);

        // Initialize main layout
        MainLayout mainLayout = new MainLayout(primaryStage);

        // Create scene
        Scene scene = new Scene(mainLayout.getMainLayout(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}