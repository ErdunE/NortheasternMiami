package ui;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import log.LogHelper;
import model.Movie;

import java.util.logging.Logger;

/**
 * Displays a detailed window for a specific movie.
 * Shows movie details such as the poster, title, rating, genres, director, overview, and a trailer.
 * The window includes fade-in and fade-out animations for a smooth user experience.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class MovieDetailsWindow {

    private static final Logger logger = LogHelper.getLogger(MovieDetailsWindow.class);

    /**
     * Displays the movie details window for the given movie.
     *
     * @param movie The movie object containing details to display.
     */
    public static void display(Movie movie) {
        logger.info("Displaying details for movie: " + movie.getTitle());

        Stage stage = initializeStage(movie);
        BorderPane root = createRootLayout();

        VBox posterBox = createPosterBox(movie);
        VBox movieDetailsBox = createMovieDetailsBox(movie);
        VBox overviewBox = createOverviewBox(movie);
        TrailerBox trailerBox = createTrailerBox(movie);

        root.setTop(createTopSection(posterBox, movieDetailsBox));
        root.setBottom(createBottomSection(overviewBox, trailerBox.box));

        // Scene and Stage setup
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            event.consume();
            applyFadeOutTransition(stage, trailerBox.webView);
        });

        stage.setOnShown(_ -> applyFadeInTransition(stage));

        stage.showAndWait();
    }

    /**
     * Initializes and sets up the movie details window stage.
     *
     * @param movie The movie object containing the title.
     * @return The initialized Stage object.
     */
    private static Stage initializeStage(Movie movie) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(movie.getTitle());
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        return stage;
    }

    /**
     * Creates the root layout for the window.
     *
     * @return A BorderPane serving as the root layout.
     */
    private static BorderPane createRootLayout() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        return root;
    }

    /**
     * Creates a VBox containing the movie poster.
     *
     * @param movie The movie object containing the poster URL.
     * @return A VBox displaying the movie poster.
     */
    private static VBox createPosterBox(Movie movie) {
        VBox posterBox = new VBox();
        posterBox.setAlignment(Pos.CENTER);
        posterBox.setPadding(new Insets(10));
        posterBox.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15px; -fx-background-radius: 15px;");

        ImageView posterView = new ImageView(movie.getPosterUrl());
        posterView.setFitWidth(200);
        posterView.setPreserveRatio(true);
        posterView.setStyle("-fx-border-radius: 15px;");

        posterBox.getChildren().add(posterView);
        return posterBox;
    }

    /**
     * Creates a VBox displaying detailed information about the movie.
     *
     * @param movie The movie object containing details.
     * @return A VBox displaying the movie details.
     */
    private static VBox createMovieDetailsBox(Movie movie) {
        VBox movieDetailsBox = new VBox(10);
        movieDetailsBox.setAlignment(Pos.TOP_LEFT);
        movieDetailsBox.setPadding(new Insets(15));
        movieDetailsBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 15px; -fx-padding: 15px;");

        Label titleLabel = new Label(movie.getTitle());
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-underline: true;");

        movieDetailsBox.getChildren().addAll(
                titleLabel,
                createDetailLabel("Rating: 🌟 ", String.valueOf(movie.getRating())),
                createDetailLabel("Rating Level: ", movie.getRatingLevel()),
                createDetailLabel("Release Date: ", movie.getReleaseDate()),
                createDetailLabel("Genres: ", String.join(", ", movie.getGenres())),
                createDetailLabel("Duration: ", movie.getDuration()),
                createDetailLabel("Director: ", movie.getDirector()),
                createDetailLabel("Cast: ", String.join(", ", movie.getCast())),
                createDetailLabel("Language: ", movie.getLanguage()),
                createDetailLabel("Revenue: ", movie.getRevenue()),
                createDetailLabel("Keywords: ", String.join(", ", movie.getKeywords()))
        );

        return movieDetailsBox;
    }

    /**
     * Creates a VBox for displaying the movie overview.
     *
     * @param movie The movie object containing the overview.
     * @return A VBox displaying the overview.
     */
    private static VBox createOverviewBox(Movie movie) {
        VBox overviewBox = new VBox(10);
        overviewBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-padding: 10px; -fx-border-radius: 15px;");
        overviewBox.setPadding(new Insets(20, 10, 10, 10));

        Label overviewTitle = new Label("Overview:");
        overviewTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Text overviewText = new Text(movie.getOverview());
        overviewText.setStyle("-fx-font-size: 14px; -fx-line-spacing: 1.5;");
        overviewText.setWrappingWidth(600);

        ScrollPane overviewScroll = new ScrollPane(overviewText);
        overviewScroll.setFitToWidth(true);
        overviewScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        overviewBox.getChildren().addAll(overviewTitle, overviewScroll);
        return overviewBox;
    }

    /**
     * A helper class that holds the trailer box and its associated WebView.
     */
    private static class TrailerBox {
        VBox box;
        WebView webView;

        /**
         * Initializes the TrailerBox with a VBox and a WebView.
         *
         * @param box     The {@link VBox} containing the trailer.
         * @param webView The {@link WebView} to play the trailer.
         */
        public TrailerBox(VBox box, WebView webView) {
            this.box = box;
            this.webView = webView;
        }
    }

    /**
     * Creates a VBox for displaying the movie trailer.
     *
     * @param movie The {@link Movie} object containing the trailer URL.
     * @return A {@link TrailerBox} containing the trailer VBox and WebView.
     */
    private static TrailerBox createTrailerBox(Movie movie) {
        VBox trailerBox = new VBox(10);
        trailerBox.setPadding(new Insets(10));
        trailerBox.setStyle("-fx-background-color: #000; -fx-border-color: #ccc; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        trailerBox.setAlignment(Pos.CENTER);

        WebView trailerView = new WebView();
        trailerView.setPrefSize(600, 300);
        String embedUrl = movie.getTrailerUrl().replace("watch?v=", "embed/") + "?autoplay=1";
        trailerView.getEngine().load(embedUrl);

        trailerBox.getChildren().add(trailerView);
        return new TrailerBox(trailerBox, trailerView);
    }

    /**
     * Creates an HBox containing the poster and movie details sections.
     *
     * @param posterBox       The {@link VBox} containing the movie poster.
     * @param movieDetailsBox The {@link VBox} containing the movie details.
     * @return An {@link HBox} with the poster and movie details.
     */
    private static HBox createTopSection(VBox posterBox, VBox movieDetailsBox) {
        HBox topSection = new HBox(20, posterBox, movieDetailsBox);
        topSection.setAlignment(Pos.TOP_CENTER);
        return topSection;
    }

    /**
     * Creates a VBox containing the overview and trailer sections.
     *
     * @param overviewBox The {@link VBox} containing the movie overview.
     * @param trailerBox  The {@link VBox} containing the movie trailer.
     * @return A {@link VBox} with the overview and trailer.
     */
    private static VBox createBottomSection(VBox overviewBox, VBox trailerBox) {
        VBox bottomSection = new VBox(20, overviewBox, trailerBox);
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setPadding(new Insets(20, 0, 0, 0));
        return bottomSection;
    }

    /**
     * Applies a fade-in transition effect when the stage is shown.
     *
     * @param stage The {@link Stage} to apply the fade-in effect.
     */
    private static void applyFadeInTransition(Stage stage) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    /**
     * Applies a fade-out transition effect and closes the stage when finished.
     *
     * @param stage       The {@link Stage} to close after the fade-out effect.
     * @param trailerView The {@link WebView} displaying the trailer.
     */
    private static void applyFadeOutTransition(Stage stage, WebView trailerView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(_ -> {
            if (trailerView != null) {
                trailerView.getEngine().load(null);
            }
            stage.close();
        });
        fadeTransition.play();
    }

    /**
     * Creates a label for displaying a detail with a specified label text and content.
     *
     * @param labelText The text for the label.
     * @param content   The content to display.
     * @return A {@link Label} containing the label text and content.
     */
    private static Label createDetailLabel(String labelText, String content) {
        return new Label(labelText + (content != null && !content.isEmpty() ? content : "Unknown"));
    }
}