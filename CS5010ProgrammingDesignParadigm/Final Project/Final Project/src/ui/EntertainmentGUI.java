package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import strategy.RecommendationStrategy;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Improved GUI for the Entertainment Recommendation System with external CSS styling.
 */
public class EntertainmentGUI extends Application {

    private final RecommendationContext context = new RecommendationContext();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Entertainment Recommendation System");

        // Title
        Label titleLabel = new Label("Welcome to the Entertainment Recommendation System");
        titleLabel.getStyleClass().add("title");

        // Description
        Label descriptionLabel = new Label("Select a recommendation strategy below:");
        descriptionLabel.getStyleClass().add("description");

        // Buttons
        Button popularButton = new Button("Popular Recommendations");
        Button genreButton = new Button("Genre-Based Recommendations");
        Button ratingButton = new Button("Rating-Based Recommendations");

        // Recommendation Display Area
        TextArea recommendationArea = new TextArea();
        recommendationArea.setEditable(false);
        recommendationArea.setPromptText("Your recommendations will appear here...");
        recommendationArea.getStyleClass().add("text-area");

        // Button Actions
        popularButton.setOnAction(e -> {
            context.setRecommendationStrategy(RecommendationFactory.createStrategy("popular", null));
            displayRecommendations(recommendationArea);
        });

        genreButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter Genre");
            dialog.setHeaderText("Enter a movie genre (e.g., Action, Drama):");
            dialog.setContentText("Genre:");

            dialog.showAndWait().ifPresent(genre -> {
                context.setRecommendationStrategy(RecommendationFactory.createStrategy("genre", genre));
                displayRecommendations(recommendationArea);
            });
        });

        ratingButton.setOnAction(e -> {
            context.setRecommendationStrategy(RecommendationFactory.createStrategy("rating", null));
            displayRecommendations(recommendationArea);
        });

        // Layout
        VBox buttonLayout = new VBox(10, popularButton, genreButton, ratingButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(15, titleLabel, descriptionLabel, buttonLayout, recommendationArea);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Create Scene
        Scene scene = new Scene(mainLayout, 500, 400);

        // Load CSS
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Display recommendations in the given text area.
     *
     * @param recommendationArea The text area to display recommendations.
     */
    private void displayRecommendations(TextArea recommendationArea) {
        List<String> recommendations = context.getRecommendations();
        recommendationArea.clear();
        recommendationArea.appendText("Recommendations:\n");
        for (String recommendation : recommendations) {
            recommendationArea.appendText("- " + recommendation + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}