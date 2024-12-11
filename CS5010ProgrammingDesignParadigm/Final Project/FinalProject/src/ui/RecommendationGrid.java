package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Represents a grid layout for displaying movie recommendations.
 * Supports loading recommendations by type, filters, or a predefined list of movies.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RecommendationGrid {

    private static final Logger logger = LogHelper.getLogger(RecommendationGrid.class);

    private final GridPane gridPane;
    private final StackPane stackPane;
    private List<Movie> recommendations;
    private final Map<String, Boolean> sortOrder = new HashMap<>();
    private final LoadingSpinner loadingSpinner = new LoadingSpinner();

    /**
     * Initializes the grid with recommendations based on a specified type.
     *
     * @param type            The type of recommendations (e.g., "popular", "rating").
     * @param additionalParam Additional parameter for the strategy, if needed.
     */
    public RecommendationGrid(String type, String additionalParam) {
        gridPane = createGridPane();
        stackPane = new StackPane(gridPane);
        loadRecommendations(type, additionalParam);
    }

    /**
     * Initializes the grid with recommendations based on provided filters.
     *
     * @param genreIds       The genre IDs for filtering.
     * @param minRating      The minimum rating for filtering.
     * @param maxRating      The maximum rating for filtering.
     * @param language       The language for filtering.
     * @param minRuntime     The minimum runtime for filtering.
     * @param maxRuntime     The maximum runtime for filtering.
     * @param year           The year for filtering.
     * @param releaseDateLte The latest release date for filtering.
     * @param certification  The certification level for filtering.
     */
    public RecommendationGrid(String genreIds, String minRating, String maxRating, String language, String minRuntime,
                              String maxRuntime, String year, String releaseDateLte, String certification) {
        gridPane = createGridPane();
        stackPane = new StackPane(gridPane);
        loadRecommendationsWithFilters(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
    }

    /**
     * Initializes the grid with a predefined list of movies.
     *
     * @param movies The list of {@link Movie} objects to display.
     */
    public RecommendationGrid(List<Movie> movies) {
        gridPane = createGridPane();
        stackPane = new StackPane(gridPane);
        loadingSpinner.showSpinner(stackPane); // 显示 Spinner

        Platform.runLater(() -> {
            this.recommendations = movies;
            displayMovies(recommendations);
            loadingSpinner.hideSpinner(stackPane); // 隐藏 Spinner
        });
    }

    /**
     * Returns the stack pane containing the grid layout.
     *
     * @return A {@link StackPane} containing the grid of movie recommendations.
     */
    public StackPane getGrid() {
        return stackPane;
    }

    /**
     * Creates a new grid pane with default properties.
     *
     * @return A {@link GridPane} configured for displaying movie cards.
     */
    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        return grid;
    }

    /**
     * Loads movie recommendations based on a strategy type.
     *
     * @param type            The type of recommendation strategy.
     * @param additionalParam Additional parameter for the strategy.
     */
    private void loadRecommendations(String type, String additionalParam) {
        logger.info("Loading recommendations of type: " + type);
        loadingSpinner.showSpinner(stackPane);

        new Thread(() -> {
            try {
                RecommendationContext context = new RecommendationContext();
                context.setRecommendationStrategy(RecommendationFactory.createStrategy(type, additionalParam));
                recommendations = context.getRecommendationsWithDetails();
                Platform.runLater(() -> {
                    displayMovies(recommendations);
                    loadingSpinner.hideSpinner(stackPane);
                });
            } catch (Exception e) {
                logger.severe("Failed to load recommendations: " + e.getMessage());
                Platform.runLater(() -> {
                    loadingSpinner.hideSpinner(stackPane);
                    showError("Failed to load recommendations.");
                });
            }
        }).start();
    }

    /**
     * Loads movie recommendations based on filter criteria.
     *
     * @param genreIds       Genre IDs for filtering.
     * @param minRating      Minimum rating for filtering.
     * @param maxRating      Maximum rating for filtering.
     * @param language       Language for filtering.
     * @param minRuntime     Minimum runtime for filtering.
     * @param maxRuntime     Maximum runtime for filtering.
     * @param year           Year for filtering.
     * @param releaseDateLte Latest release date for filtering.
     * @param certification  Certification level for filtering.
     */
    private void loadRecommendationsWithFilters(String genreIds, String minRating, String maxRating, String language,
                                                String minRuntime, String maxRuntime, String year, String releaseDateLte,
                                                String certification) {
        logger.info("Loading recommendations with filters.");
        loadingSpinner.showSpinner(stackPane);

        new Thread(() -> {
            try {
                TMDBService tmdbService = new TMDBService();
                recommendations = tmdbService.fetchMoviesWithFilters(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
                Platform.runLater(() -> {
                    displayMovies(recommendations);
                    loadingSpinner.hideSpinner(stackPane);
                });
            } catch (IOException | InterruptedException e) {
                logger.severe("Failed to load recommendations with filters: " + e.getMessage());
                Platform.runLater(() -> {
                    loadingSpinner.hideSpinner(stackPane);
                    showError("Failed to load filtered recommendations.");
                });
            }
        }).start();
    }

    /**
     * Displays a list of movies on the grid.
     *
     * @param recommendations The list of {@link Movie} objects to display.
     */
    private void displayMovies(List<Movie> recommendations) {
        gridPane.getChildren().clear();

        if (recommendations == null || recommendations.isEmpty()) {
            Label noResultsLabel = new Label("No movies match your filters. Please try different criteria.");
            gridPane.getChildren().add(noResultsLabel);
            logger.info("No recommendations found.");
            return;
        }

        int col = 0, row = 0;
        for (Movie movie : recommendations) {
            MovieCard movieCard = new MovieCard(movie);
            gridPane.add(movieCard.getMovieCard(), col++, row);

            if (col == 4) {
                col = 0;
                row++;
            }
        }
        logger.info("Displayed " + recommendations.size() + " movies.");
    }

    /**
     * Sorts the displayed movies based on the specified criteria.
     *
     * @param criteria The sorting criteria (e.g., "date", "rating", "popularity").
     */
    public void sortMovies(String criteria) {
        if (recommendations == null) {
            logger.warning("No recommendations available to sort.");
            return;
        }

        recommendations = new ArrayList<>(recommendations);

        boolean ascending = sortOrder.getOrDefault(criteria, false);
        logger.info("Sorting movies by: " + criteria + " in " + (ascending ? "ascending" : "descending") + " order.");

        switch (criteria) {
            case "date":
                recommendations.sort((m1, m2) -> ascending ?
                        m1.getReleaseDate().compareTo(m2.getReleaseDate()) :
                        m2.getReleaseDate().compareTo(m1.getReleaseDate()));
                break;
            case "rating":
                recommendations.sort((m1, m2) -> ascending ?
                        Double.compare(m1.getRating(), m2.getRating()) :
                        Double.compare(m2.getRating(), m1.getRating()));
                break;
            case "popularity":
                recommendations.sort((m1, m2) -> ascending ?
                        Long.compare(parseRevenue(m1.getRevenue()), parseRevenue(m2.getRevenue())) :
                        Long.compare(parseRevenue(m2.getRevenue()), parseRevenue(m1.getRevenue())));
                break;
            default:
                logger.warning("Unknown sorting criteria: " + criteria);
        }

        sortOrder.put(criteria, !ascending);
        displayMovies(recommendations);
    }

    /**
     * Parses a revenue string to a long value.
     *
     * @param revenue The revenue string.
     * @return The parsed revenue as a long value.
     */
    private long parseRevenue(String revenue) {
        try {
            return Long.parseLong(revenue.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            logger.warning("Failed to parse revenue: " + revenue);
            return 0;
        }
    }

    /**
     * Displays an error message on the grid.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        Label errorLabel = new Label(message);
        gridPane.getChildren().clear();
        gridPane.add(errorLabel, 0, 0);
    }
}