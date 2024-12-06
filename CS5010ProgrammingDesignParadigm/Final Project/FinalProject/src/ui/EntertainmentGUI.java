package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntertainmentGUI extends Application {

    private static EntertainmentGUI instance;

    @Override
    public void start(Stage primaryStage) {
        instance = this;

        primaryStage.setTitle("Entertainment Recommendation System");
        primaryStage.setMaximized(true);

        MainLayout mainLayout = new MainLayout(primaryStage);

        Scene scene = new Scene(mainLayout.getMainLayout(), 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static EntertainmentGUI getInstance() {
        return instance;
    }

    public static javafx.application.HostServices getHostServicesInstance() {
        return getInstance().getHostServices();
    }
}