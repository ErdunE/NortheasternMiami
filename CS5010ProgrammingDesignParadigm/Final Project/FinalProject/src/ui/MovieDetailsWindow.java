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

public class MovieDetailsWindow {

    private static final Logger logger = LogHelper.getLogger(MovieDetailsWindow.class);

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

    private static Stage initializeStage(Movie movie) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(movie.getTitle());
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        return stage;
    }

    private static BorderPane createRootLayout() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        return root;
    }

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

    private static class TrailerBox {
        VBox box;
        WebView webView;

        public TrailerBox(VBox box, WebView webView) {
            this.box = box;
            this.webView = webView;
        }
    }

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

    private static HBox createTopSection(VBox posterBox, VBox movieDetailsBox) {
        HBox topSection = new HBox(20, posterBox, movieDetailsBox);
        topSection.setAlignment(Pos.TOP_CENTER);
        return topSection;
    }

    private static VBox createBottomSection(VBox overviewBox, VBox trailerBox) {
        VBox bottomSection = new VBox(20, overviewBox, trailerBox);
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setPadding(new Insets(20, 0, 0, 0));
        return bottomSection;
    }

    private static void applyFadeInTransition(Stage stage) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

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

    private static Label createDetailLabel(String labelText, String content) {
        return new Label(labelText + (content != null && !content.isEmpty() ? content : "Unknown"));
    }
}