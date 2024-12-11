package ui;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;

/**
 * Provides a loading spinner to indicate background processing.
 * The spinner can be shown or hidden dynamically within a StackPane.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class LoadingSpinner {

    private final ProgressIndicator spinner;

    /**
     * Initializes the LoadingSpinner with a hidden progress indicator.
     * The progress is set to indeterminate.
     */
    public LoadingSpinner() {
        spinner = new ProgressIndicator();
        spinner.setProgress(-1);
        spinner.setVisible(false);
    }

    /**
     * Displays the loading spinner in the center of the specified StackPane.
     * If the spinner is not already present, it is added to the StackPane.
     *
     * @param root The StackPane where the spinner will be displayed.
     */
    public void showSpinner(StackPane root) {
        if (!spinner.isVisible()) {
            spinner.setVisible(true);
            if (!root.getChildren().contains(spinner)) {
                root.getChildren().add(spinner);
            }
            StackPane.setAlignment(spinner, Pos.CENTER);
        }
    }

    /**
     * Hides the loading spinner and removes it from the specified StackPane.
     *
     * @param root The StackPane from which the spinner will be removed.
     */
    public void hideSpinner(StackPane root) {
        if (spinner.isVisible()) {
            spinner.setVisible(false);
            root.getChildren().remove(spinner);
        }
    }
}
