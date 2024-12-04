import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TestJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("JavaFX is working!");
        Scene scene = new Scene(label, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}