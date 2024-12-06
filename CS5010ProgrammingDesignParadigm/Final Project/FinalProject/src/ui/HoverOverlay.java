package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Movie;

public class HoverOverlay {

    private final StackPane overlay;

    public HoverOverlay(Movie movie) {
        VBox overlayContent = new VBox(10);
        overlayContent.setAlignment(Pos.TOP_LEFT);
        overlayContent.setPadding(new Insets(15));

        Label overviewTitle = new Label("Overview");
        overviewTitle.getStyleClass().add("hover-section-title");

        Label overviewText = new Label(movie.getOverview());
        overviewText.setWrapText(true);
        overviewText.getStyleClass().add("hover-overlay");

        Label additionalInfo = new Label(
                "Duration: " + (movie.getDuration() != null ? movie.getDuration() : "Unknown") + "\n" +
                   "Rating: " + (movie.getRating() > 0 ? movie.getRating() : "Unknown") + "\n" +
                   "Language: " + (movie.getLanguage() != null ? movie.getLanguage() : "Unknown") + "\n" +
                   "Keywords: " + (movie.getKeywords() != null ? String.join(", ", movie.getKeywords()) : "Unknown") + "\n" +
                   "Cast: " + (movie.getCast() != null ? String.join(", ", movie.getCast()) : "Unknown")
        );
        additionalInfo.getStyleClass().add("hover-overlay");

        overlayContent.getChildren().addAll(overviewTitle, overviewText, additionalInfo);

        overlay = new StackPane(overlayContent);
        overlay.getStyleClass().add("movie-card-overlay");
        overlay.setAlignment(Pos.CENTER);
        overlay.setVisible(false);

        overlay.setMouseTransparent(true);

    }

    public StackPane getOverlay() {
        return overlay;
    }
}