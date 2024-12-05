package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.Movie;

import java.util.List;

public class EntertainmentGUI extends Application {

    private final RecommendationContext context = new RecommendationContext();
    private Stage primaryStage; // Store a reference to the primary stage

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Entertainment Recommendation System");
        primaryStage.setMaximized(true);

        Text titleText = createGradientTitle();

        HBox menuBar = createMenuBar();

        ScrollPane recommendationScrollPane = new ScrollPane();
        recommendationScrollPane.setFitToWidth(true);
        recommendationScrollPane.setStyle("-fx-background: transparent;");

        VBox recommendationBox = createRecommendationLayout("popular", null);
        recommendationScrollPane.setContent(recommendationBox);

        VBox mainLayout = new VBox(20, titleText, menuBar, recommendationScrollPane);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.getStyleClass().add("root");

        Scene scene = new Scene(mainLayout, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createMenuBar() {
        Button popularButton = new Button("Popular");
        Button genreButton = new Button("By Genre");
        Button ratingButton = new Button("By Rating");
        Button filterButton = new Button("Filter");

        popularButton.setOnAction(e -> switchTab("popular", null));
        genreButton.setOnAction(e -> switchTab("genre", null)); // Add dropdown for genres
        ratingButton.setOnAction(e -> switchTab("rating", null));
        filterButton.setOnAction(e -> showFilterDialog());

        HBox menuBar = new HBox(15, popularButton, genreButton, ratingButton, filterButton);
        menuBar.setAlignment(Pos.CENTER);
        menuBar.getStyleClass().add("menu-bar");
        menuBar.setPadding(new Insets(10, 20, 10, 20));

        return menuBar;
    }

    private void switchTab(String type, String additionalParam) {
        VBox newContent = createRecommendationLayout(type, additionalParam);
        ScrollPane recommendationScrollPane = (ScrollPane) ((VBox) primaryStage.getScene().getRoot()).getChildren().get(2);
        recommendationScrollPane.setContent(newContent);
    }

    private VBox createRecommendationLayout(String type, String additionalParam) {
        VBox recommendationBox = new VBox(20);
        recommendationBox.setPadding(new Insets(20));

        context.setRecommendationStrategy(RecommendationFactory.createStrategy(type, additionalParam));
        List<Movie> recommendations = context.getRecommendationsWithDetails();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);

        int col = 0, row = 0;
        for (Movie movie : recommendations) {
            StackPane movieCard = createMovieCard(movie);
            gridPane.add(movieCard, col++, row);

            if (col == 4) {
                col = 0;
                row++;
            }
        }

        recommendationBox.getChildren().add(gridPane);
        return recommendationBox;
    }

    private StackPane createMovieCard(Movie movie) {
        ImageView poster = new ImageView(movie.getPosterUrl());
        poster.setFitWidth(180);
        poster.setPreserveRatio(true);

        Label title = new Label(movie.getTitle());
        title.getStyleClass().add("movie-title");

        Label rating = new Label("Rating: " + movie.getRating());
        rating.getStyleClass().add("movie-details");

        Label genres = new Label("Genres: " + (movie.getGenres().isEmpty() ? "Unknown" : String.join(", ", movie.getGenres())));
        genres.getStyleClass().add("movie-details");

        Label directorLabel = new Label("Director: " + (movie.getDirector() != null ? movie.getDirector() : "Unknown"));
        directorLabel.getStyleClass().add("movie-details");

        Label releaseYearLabel = new Label("Year: " + (movie.getReleaseYear() > 0 ? movie.getReleaseYear() : "Unknown"));
        releaseYearLabel.getStyleClass().add("movie-details");

        VBox detailsBox = new VBox(5, title, rating, genres, directorLabel, releaseYearLabel);
        detailsBox.setAlignment(Pos.CENTER);

        VBox movieCardContent = new VBox(10, poster, detailsBox);
        movieCardContent.setAlignment(Pos.CENTER);

        StackPane overlay = createHoverOverlay(movie);

        StackPane movieCard = new StackPane(movieCardContent, overlay);
        movieCard.getStyleClass().add("movie-card");

        // Mouse Events
        movieCard.setOnMouseEntered(e -> overlay.setVisible(true));
        movieCard.setOnMouseExited(e -> overlay.setVisible(false));

        return movieCard;
    }

    private StackPane createHoverOverlay(Movie movie) {
        VBox overlayContent = new VBox(10);
        overlayContent.setAlignment(Pos.TOP_LEFT);
        overlayContent.setPadding(new Insets(15));

        Label overviewTitle = new Label("Overview");
        overviewTitle.getStyleClass().add("hover-section-title");

        Label overviewText = new Label(movie.getOverview());
        overviewText.setWrapText(true);
        overviewText.getStyleClass().add("hover-overlay");

        Label additionalInfo = new Label(
                "Duration: 2h 12m\n" +
                        "Rating: PG-13\n" +
                        "Language: English\n" +
                        "Keywords: Adventure, Fantasy, Animation"
        );
        additionalInfo.getStyleClass().add("hover-overlay");

        overlayContent.getChildren().addAll(overviewTitle, overviewText, additionalInfo);

        StackPane overlay = new StackPane(overlayContent);
        overlay.getStyleClass().add("movie-card-overlay");
        overlay.setAlignment(Pos.CENTER);
        overlay.setVisible(false);

        return overlay;
    }

    private Text createGradientTitle() {
        Text titleText = new Text("Entertainment Recommendation System");
        titleText.setFont(Font.font("Bebas Neue", FontWeight.BOLD, 40));

        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(229, 9, 20)),
                new Stop(1, Color.rgb(184, 29, 36))
        );
        titleText.setFill(gradient);
        titleText.setEffect(new DropShadow(4, Color.BLACK));

        return titleText;
    }

    private void showFilterDialog() {
        Alert filterDialog = new Alert(Alert.AlertType.INFORMATION);
        filterDialog.setTitle("Filter");
        filterDialog.setHeaderText("Filter Movies");
        filterDialog.setContentText("Filter options will be implemented.");
        filterDialog.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}