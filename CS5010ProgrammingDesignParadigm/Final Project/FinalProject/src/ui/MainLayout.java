package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents the main layout of the Entertainment Recommendation System.
 * This layout includes the title, menu bar, sorting buttons, and the movie recommendations grid.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class MainLayout {

    private static final Logger logger = LogHelper.getLogger(MainLayout.class);

    private final VBox mainLayout;
    private RecommendationGrid recommendationGrid;
    private final ScrollPane recommendationScrollPane;
    private final HBox sortButtons;

    /**
     * Initializes the main layout with the title, menu bar, sort buttons, and recommendation grid.
     *
     * @param primaryStage The primary stage of the application.
     */
    public MainLayout(Stage primaryStage) {
        Text titleText = TitleComponent.createGradientTitle();
        MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage, this);

        recommendationScrollPane = createRecommendationScrollPane();
        recommendationGrid = new RecommendationGrid("popular", null);
        recommendationScrollPane.setContent(recommendationGrid.getGrid());

        sortButtons = createSortButtons();

        mainLayout = createMainLayout(titleText, menuBarComponent);
    }

    /**
     * Gets the main layout of the application.
     *
     * @return The VBox representing the main layout.
     */
    public VBox getMainLayout() {
        return mainLayout;
    }

    /**
     * Creates the main layout with the title, menu bar, sort buttons, and recommendation scroll pane.
     *
     * @param titleText The gradient title text.
     * @param menuBarComponent The menu bar component.
     * @return The VBox containing the main layout elements.
     */
    private VBox createMainLayout(Text titleText, MenuBarComponent menuBarComponent) {
        VBox layout = new VBox(20, titleText, menuBarComponent.getMenuBar(), sortButtons, recommendationScrollPane);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("root");
        return layout;
    }

    /**
     * Creates a scroll pane for displaying the recommendation grid.
     *
     * @return The configured ScrollPane.
     */
    private ScrollPane createRecommendationScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");
        return scrollPane;
    }

    /**
     * Creates the sort buttons for sorting movies by different criteria.
     *
     * @return The HBox containing the sort buttons.
     */
    private HBox createSortButtons() {
        Button sortByDateButton = createSortButton("Sort by Release Date", "date");
        Button sortByRatingButton = createSortButton("Sort by Rating", "rating");
        Button sortByPopularityButton = createSortButton("Sort by Popularity", "popularity");

        HBox buttons = new HBox(10, sortByDateButton, sortByRatingButton, sortByPopularityButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        return buttons;
    }

    /**
     * Creates a single sort button with specified text and sort criteria.
     *
     * @param buttonText The text to display on the button.
     * @param sortCriteria The criteria for sorting movies.
     * @return The configured Button.
     */
    private Button createSortButton(String buttonText, String sortCriteria) {
        Button button = new Button(buttonText);
        button.setOnAction(e -> {
            logger.info("Sorting movies by: " + sortCriteria);
            recommendationGrid.sortMovies(sortCriteria);
        });
        return button;
    }

    /**
     * Updates the recommendation grid with new content.
     *
     * @param newGrid The new RecommendationGrid to display.
     */
    public void updateRecommendationGrid(RecommendationGrid newGrid) {
        logger.info("Updating recommendation grid.");
        this.recommendationGrid = newGrid;
        recommendationScrollPane.setContent(newGrid.getGrid());
    }

    /**
     * Performs a movie search based on the given query and updates the recommendation grid.
     *
     * @param query The search query string.
     */
    public void performSearch(String query) {
        logger.info("Performing search with query: " + query);
        try {
            TMDBService tmdbService = new TMDBService();
            List<Movie> searchResults = tmdbService.searchMovies(query);
            RecommendationGrid newGrid = new RecommendationGrid(searchResults);
            updateRecommendationGrid(newGrid);
            logger.info("Search completed successfully.");
        } catch (IOException | InterruptedException e) {
            logger.severe("Search failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}