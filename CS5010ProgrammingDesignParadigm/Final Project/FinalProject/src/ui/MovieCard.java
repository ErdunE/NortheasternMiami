package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import log.LogHelper;
import model.Movie;

import java.util.logging.Logger;

/**
 * Represents a movie card component in the Entertainment Recommendation System.
 * Displays movie details, including the poster, title, rating, genres, director, and release year.
 * Supports hover effects to show more details and click events to open a detailed view.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class MovieCard {

    private static final Logger logger = LogHelper.getLogger(MovieCard.class);

    private static final double POSTER_WIDTH = 180;
    private static final int SPACING = 10;
    private static final int DETAILS_SPACING = 5;
    private final StackPane movieCard;

    /**
     * Creates a MovieCard instance with the specified movie details.
     *
     * @param movie The movie object containing details to display.
     */
    public MovieCard(Movie movie) {
        ImageView poster = createPosterImageView(movie);
        VBox detailsBox = createDetailsBox(movie);

        VBox movieCardContent = new VBox(SPACING, poster, detailsBox);
        movieCardContent.setAlignment(Pos.CENTER);

        HoverOverlay hoverOverlay = new HoverOverlay(movie);

        movieCard = new StackPane(movieCardContent, hoverOverlay.getOverlay());
        movieCard.getStyleClass().add("movie-card");

        addHoverEffects(hoverOverlay);
        addClickEvent(movie);
    }

    /**
     * Creates an ImageView for the movie poster.
     *
     * @param movie The movie object containing the poster URL.
     * @return The ImageView displaying the movie poster.
     */
    private ImageView createPosterImageView(Movie movie) {
        ImageView poster = new ImageView();
        try {
            poster.setImage(new Image(movie.getPosterUrl(), true));
        } catch (Exception e) {
            logger.warning("Failed to load poster image. Using default image.");
            poster.setImage(new Image("/resources/default-poster.png")); // 默认图片路径
        }
        poster.setFitWidth(POSTER_WIDTH);
        poster.setPreserveRatio(true);
        return poster;
    }

    /**
     * Creates a VBox containing the movie details like title, rating, genres, director, and release year.
     *
     * @param movie The movie object containing the details.
     * @return The VBox displaying the movie details.
     */
    private VBox createDetailsBox(Movie movie) {
        Label title = createStyledLabel(movie.getTitle(), "movie-title");
        Label rating = createStyledLabel("Rating: 🌟" + movie.getRating(), "movie-details");
        Label genres = createStyledLabel("Genres: " + getFormattedGenres(movie), "movie-details");
        Label director = createStyledLabel("Director: " + getFormattedDirector(movie), "movie-details");
        Label releaseYear = createStyledLabel("Year: " + getFormattedReleaseYear(movie), "movie-details");

        VBox detailsBox = new VBox(DETAILS_SPACING, title, rating, genres, director, releaseYear);
        detailsBox.setAlignment(Pos.CENTER);
        return detailsBox;
    }

    /**
     * Creates a styled Label with the specified text and style class.
     *
     * @param text       The text to display in the label.
     * @param styleClass The CSS style class for the label.
     * @return The styled Label instance.
     */
    private Label createStyledLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }

    /**
     * Formats the genres list into a comma-separated string.
     *
     * @param movie The movie object containing the genres.
     * @return A formatted string of genres or "Unknown" if no genres are available.
     */
    private String getFormattedGenres(Movie movie) {
        return movie.getGenres().isEmpty() ? "Unknown" : String.join(", ", movie.getGenres());
    }

    /**
     * Formats the director's name or returns "Unknown" if the director is not available.
     *
     * @param movie The movie object containing the director's name.
     * @return The director's name or "Unknown".
     */
    private String getFormattedDirector(Movie movie) {
        return movie.getDirector() != null ? movie.getDirector() : "Unknown";
    }

    /**
     * Formats the release year or returns "Unknown" if the year is not available.
     *
     * @param movie The movie object containing the release year.
     * @return The release year or "Unknown".
     */
    private String getFormattedReleaseYear(Movie movie) {
        return movie.getReleaseYear() > 0 ? String.valueOf(movie.getReleaseYear()) : "Unknown";
    }

    /**
     * Adds hover effects to the movie card to display the overlay when the mouse enters.
     *
     * @param hoverOverlay The HoverOverlay instance for displaying additional details.
     */
    private void addHoverEffects(HoverOverlay hoverOverlay) {
        movieCard.setOnMouseEntered(e -> hoverOverlay.getOverlay().setVisible(true));
        movieCard.setOnMouseExited(e -> hoverOverlay.getOverlay().setVisible(false));
    }

    /**
     * Adds a click event to the movie card to display detailed information in a new window.
     *
     * @param movie The movie object to display details for.
     */
    private void addClickEvent(Movie movie) {
        movieCard.setOnMouseClicked(e -> {
            logger.info("Movie card clicked: " + movie.getTitle());
            MovieDetailsWindow.display(movie);
        });
    }

    /**
     * Returns the movie card component.
     *
     * @return The StackPane representing the movie card.
     */
    public StackPane getMovieCard() {
        return movieCard;
    }
}