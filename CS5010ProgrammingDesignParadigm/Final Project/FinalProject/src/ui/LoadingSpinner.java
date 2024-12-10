package ui;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;

public class LoadingSpinner {

    private final ProgressIndicator spinner;

    public LoadingSpinner() {
        spinner = new ProgressIndicator();
        spinner.setProgress(-1);
        spinner.setVisible(false);
    }

    public void show(StackPane root) {
        spinner.setVisible(true);
        if (!root.getChildren().contains(spinner)) {
            root.getChildren().add(spinner);
        }
        StackPane.setAlignment(spinner, Pos.CENTER);
    }

    public void hide(StackPane root) {
        spinner.setVisible(false);
        root.getChildren().remove(spinner);
    }

    public ProgressIndicator getSpinner() {
        return spinner;
    }
}
