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
import model.Movie;

public class MovieDetailsWindow {

    public static void display(Movie movie) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(movie.getTitle());
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        // Root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 15px; -fx-background-radius: 15px;");

        // Left: Movie Poster
        VBox posterBox = new VBox();
        posterBox.setAlignment(Pos.CENTER);
        posterBox.setPadding(new Insets(10));
        posterBox.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 15px; -fx-background-radius: 15px;");

        ImageView posterView = new ImageView(movie.getPosterUrl());
        posterView.setFitWidth(200);
        posterView.setPreserveRatio(true);
        posterView.setStyle("-fx-border-radius: 15px;"); // Ensure the poster itself respects the border radius

        posterBox.getChildren().add(posterView);

        // Right: Movie Details
        VBox movieDetailsBox = new VBox(10);
        movieDetailsBox.setAlignment(Pos.TOP_LEFT);
        movieDetailsBox.setPadding(new Insets(15));
        movieDetailsBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 15px; -fx-padding: 15px;");

        // Title
        Label titleLabel = new Label(movie.getTitle());
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-border-width: 0 0 2 0; -fx-border-color: #ddd; -fx-padding: 0 0 10 0;");

        Label ratingLabel = new Label("Rating: ★ " + movie.getRating());
        Label durationLabel = new Label("Duration: " + movie.getDuration());
        Label genresLabel = new Label("Genres: " + String.join(", ", movie.getGenres()));
        Label languageLabel = new Label("Language: " + movie.getLanguage());
        Label directorLabel = new Label("Director: " + movie.getDirector());
        Label castLabel = new Label("Cast: " + String.join(", ", movie.getCast()));

        movieDetailsBox.getChildren().addAll(titleLabel, ratingLabel, durationLabel, genresLabel, languageLabel, directorLabel, castLabel);

        // Bottom: Movie Overview
        VBox overviewBox = new VBox(10);
        overviewBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-radius: 15px; -fx-padding: 15px;");
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


        // Bottom: Trailer WebView
        VBox trailerBox = new VBox(10);
        trailerBox.setPadding(new Insets(10));
        trailerBox.setStyle("-fx-background-color: #000; -fx-border-color: #ccc; -fx-border-radius: 15px; -fx-background-radius: 15px;");
        trailerBox.setAlignment(Pos.CENTER);

        WebView trailerView = new WebView();
        trailerView.setPrefSize(600, 300);
        String embedUrl = movie.getTrailerUrl().replace("watch?v=", "embed/") + "?autoplay=1";
        trailerView.getEngine().load(embedUrl);

        trailerBox.getChildren().add(trailerView);

        // Combine layouts: poster and details on top, overview and trailer on bottom
        HBox topSection = new HBox(20, posterBox, movieDetailsBox);
        topSection.setAlignment(Pos.TOP_CENTER);

        VBox bottomSection = new VBox(20, overviewBox, trailerBox);
        bottomSection.setAlignment(Pos.CENTER_LEFT);
        bottomSection.setPadding(new Insets(20, 0, 0, 0)); // Add space between overview and trailer

        root.setTop(topSection);
        root.setBottom(bottomSection);

        // Scene and Stage setup
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Stop trailer on close
        stage.setOnCloseRequest(event -> {
            event.consume(); // 防止直接关闭
            applyFadeTransition(stage, false, trailerView); // 确保关闭时停止视频
        });

        // Add fade-in effect
        stage.setOnShown(event -> applyFadeTransition(stage, true, null));

        stage.showAndWait();
    }

    private static void applyFadeTransition(Stage stage, boolean fadeIn, WebView trailerView) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), stage.getScene().getRoot());
        fadeTransition.setFromValue(fadeIn ? 0 : 1);
        fadeTransition.setToValue(fadeIn ? 1 : 0);
        fadeTransition.setOnFinished(event -> {
            if (!fadeIn) {

                if (trailerView != null) {
                    trailerView.getEngine().load(null);
                }
                stage.close();
            }
        });
        fadeTransition.play();
    }
}