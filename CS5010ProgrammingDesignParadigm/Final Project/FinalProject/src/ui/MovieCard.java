package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Movie;

public class MovieCard {

    private final StackPane movieCard;

    public MovieCard(Movie movie) {
        ImageView poster = new ImageView(movie.getPosterUrl());
        poster.setFitWidth(180);
        poster.setPreserveRatio(true);

        Label title = new Label(movie.getTitle());
        title.getStyleClass().add("movie-title");

        // Add additional details
        Label rating = new Label("Rating: " + movie.getRating());
        rating.getStyleClass().add("movie-details");

        Label genres = new Label("Genres: " + (movie.getGenres().isEmpty() ? "Unknown" : String.join(", ", movie.getGenres())));
        genres.getStyleClass().add("movie-details");

        Label director = new Label("Director: " + (movie.getDirector() != null ? movie.getDirector() : "Unknown"));
        director.getStyleClass().add("movie-details");

        Label releaseYearLabel = new Label("Year: " + (movie.getReleaseYear() > 0 ? movie.getReleaseYear() : "Unknown"));
        releaseYearLabel.getStyleClass().add("movie-details");

        VBox detailsBox = new VBox(5, title, rating, genres, director, releaseYearLabel);
        detailsBox.setAlignment(Pos.CENTER);

        VBox movieCardContent = new VBox(10, poster, detailsBox);
        movieCardContent.setAlignment(Pos.CENTER);

        HoverOverlay hoverOverlay = new HoverOverlay(movie);

        movieCard = new StackPane(movieCardContent, hoverOverlay.getOverlay());
        movieCard.getStyleClass().add("movie-card");

        movieCard.setOnMouseEntered(e -> hoverOverlay.getOverlay().setVisible(true));
        movieCard.setOnMouseExited(e -> hoverOverlay.getOverlay().setVisible(false));

        movieCard.setOnMouseClicked(e -> MovieDetailsWindow.display(movie));
    }

    public StackPane getMovieCard() {
        return movieCard;
    }
}