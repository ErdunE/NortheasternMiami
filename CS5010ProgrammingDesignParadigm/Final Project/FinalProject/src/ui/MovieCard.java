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

public class MovieCard {

    private static final Logger logger = LogHelper.getLogger(MovieCard.class);

    private static final double POSTER_WIDTH = 180;
    private static final int SPACING = 10;
    private static final int DETAILS_SPACING = 5;
    private final StackPane movieCard;

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

    private Label createStyledLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }

    private String getFormattedGenres(Movie movie) {
        return movie.getGenres().isEmpty() ? "Unknown" : String.join(", ", movie.getGenres());
    }

    private String getFormattedDirector(Movie movie) {
        return movie.getDirector() != null ? movie.getDirector() : "Unknown";
    }

    private String getFormattedReleaseYear(Movie movie) {
        return movie.getReleaseYear() > 0 ? String.valueOf(movie.getReleaseYear()) : "Unknown";
    }

    private void addHoverEffects(HoverOverlay hoverOverlay) {
        movieCard.setOnMouseEntered(e -> hoverOverlay.getOverlay().setVisible(true));
        movieCard.setOnMouseExited(e -> hoverOverlay.getOverlay().setVisible(false));
    }

    private void addClickEvent(Movie movie) {
        movieCard.setOnMouseClicked(e -> {
            logger.info("Movie card clicked: " + movie.getTitle());
            MovieDetailsWindow.display(movie);
        });
    }

    public StackPane getMovieCard() {
        return movieCard;
    }
}