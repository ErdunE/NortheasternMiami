package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import log.LogHelper;
import model.Movie;

import java.util.logging.Logger;

/**
 * Creates a hover overlay for displaying additional movie details.
 * The overlay includes the movie overview, duration, rating, language,
 * keywords, and cast information. It appears when a user hovers over a movie card.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class HoverOverlay {

    private static final Logger logger = LogHelper.getLogger(HoverOverlay.class);

    private static final String SECTION_TITLE_CLASS = "hover-section-title";
    private static final String OVERLAY_CONTENT_CLASS = "hover-overlay";
    private static final String MOVIE_CARD_OVERLAY_CLASS = "movie-card-overlay";

    private final StackPane overlay;

    /**
     * Constructs a HoverOverlay for a specific movie.
     *
     * @param movie The movie object containing details for the overlay.
     */
    public HoverOverlay(Movie movie) {
        logger.info("Creating hover overlay for movie: " + movie.getTitle());

        VBox overlayContent = createOverlayContent(movie);
        overlay = new StackPane(overlayContent);
        overlay.getStyleClass().add(MOVIE_CARD_OVERLAY_CLASS);
        overlay.setAlignment(Pos.CENTER);
        overlay.setVisible(false);
    }

    /**
     * Creates the content for the overlay, including the movie overview
     * and additional information such as duration, rating, language, keywords, and cast.
     *
     * @param movie The movie object containing details for the overlay.
     * @return A VBox containing the overlay content.
     */
    private VBox createOverlayContent(Movie movie) {
        VBox overlayContent = new VBox(10);
        overlayContent.setAlignment(Pos.TOP_LEFT);
        overlayContent.setPadding(new Insets(15));

        Label overviewTitle = createLabel("Overview", SECTION_TITLE_CLASS);
        Label overviewText = createLabel(movie.getOverview(), OVERLAY_CONTENT_CLASS);
        overviewText.setWrapText(true);

        Label additionalInfo = createLabel(buildAdditionalInfoText(movie), OVERLAY_CONTENT_CLASS);

        overlayContent.getChildren().addAll(overviewTitle, overviewText, additionalInfo);
        return overlayContent;
    }

    /**
     * Builds a string containing additional movie information such as
     * duration, rating, language, keywords, and cast.
     *
     * @param movie The movie object containing details to include.
     * @return A formatted string with additional movie information.
     */
    private String buildAdditionalInfoText(Movie movie) {
        return String.format(
                "Duration: %s%nRating: 🌟%s%nLanguage: %s%nKeywords: %s%nCast: %s",
                movie.getDuration() != null ? movie.getDuration() : "Unknown",
                movie.getRating() > 0 ? movie.getRating() : "Unknown",
                movie.getLanguage() != null ? movie.getLanguage() : "Unknown",
                movie.getKeywords() != null ? String.join(", ", movie.getKeywords()) : "Unknown",
                movie.getCast() != null ? String.join(", ", movie.getCast()) : "Unknown"
        );
    }

    /**
     * Creates a Label with specified text and style class.
     *
     * @param text       The text to display in the label.
     * @param styleClass The CSS style class to apply to the label.
     * @return A new Label with the given text and style class.
     */
    private Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }

    /**
     * Retrieves the StackPane representing the hover overlay.
     *
     * @return The StackPane containing the hover overlay.
     */
    public StackPane getOverlay() {
        return overlay;
    }
}