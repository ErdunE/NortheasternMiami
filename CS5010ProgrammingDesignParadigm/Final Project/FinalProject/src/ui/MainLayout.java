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

public class MainLayout {

    private static final Logger logger = LogHelper.getLogger(MainLayout.class);

    private final VBox mainLayout;
    private RecommendationGrid recommendationGrid;
    private final ScrollPane recommendationScrollPane;
    private final HBox sortButtons;

    public MainLayout(Stage primaryStage) {
        Text titleText = TitleComponent.createGradientTitle();
        MenuBarComponent menuBarComponent = new MenuBarComponent(primaryStage, this);

        recommendationScrollPane = createRecommendationScrollPane();
        recommendationGrid = new RecommendationGrid("popular", null);
        recommendationScrollPane.setContent(recommendationGrid.getGrid());

        sortButtons = createSortButtons();

        mainLayout = createMainLayout(titleText, menuBarComponent);
    }

    public VBox getMainLayout() {
        return mainLayout;
    }

    private VBox createMainLayout(Text titleText, MenuBarComponent menuBarComponent) {
        VBox layout = new VBox(20, titleText, menuBarComponent.getMenuBar(), sortButtons, recommendationScrollPane);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));
        layout.getStyleClass().add("root");
        return layout;
    }

    private ScrollPane createRecommendationScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");
        return scrollPane;
    }

    private HBox createSortButtons() {
        Button sortByDateButton = createSortButton("Sort by Release Date", "date");
        Button sortByRatingButton = createSortButton("Sort by Rating", "rating");
        Button sortByPopularityButton = createSortButton("Sort by Popularity", "popularity");

        HBox buttons = new HBox(10, sortByDateButton, sortByRatingButton, sortByPopularityButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));

        return buttons;
    }

    private Button createSortButton(String buttonText, String sortCriteria) {
        Button button = new Button(buttonText);
        button.setOnAction(e -> {
            logger.info("Sorting movies by: " + sortCriteria);
            recommendationGrid.sortMovies(sortCriteria);
        });
        return button;
    }

    public void updateRecommendationGrid(RecommendationGrid newGrid) {
        logger.info("Updating recommendation grid.");
        this.recommendationGrid = newGrid;
        recommendationScrollPane.setContent(newGrid.getGrid());
    }

    public void performSearch(String query) {
        logger.info("Performing search with query: " + query);
        try {
            TMDBService tmdbService = new TMDBService();
            List<Movie> searchResults = tmdbService.searchMovies(query);
            RecommendationGrid searchGrid = new RecommendationGrid(searchResults);
            updateRecommendationGrid(searchGrid);
            logger.info("Search completed successfully.");
        } catch (IOException | InterruptedException e) {
            logger.severe("Search failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}