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

public class RecommendationGrid {

    private static final Logger logger = LogHelper.getLogger(RecommendationGrid.class);

    private final GridPane gridPane;
    private final StackPane stackPane;
    private List<Movie> recommendations;
    private final Map<String, Boolean> sortOrder = new HashMap<>();
    private final LoadingSpinner loadingSpinner = new LoadingSpinner();

    public RecommendationGrid(String type, String additionalParam) {
        gridPane = createGridPane();
        stackPane = new StackPane(gridPane);
        loadRecommendations(type, additionalParam);
    }

    public RecommendationGrid(String genreIds, String minRating, String maxRating, String language, String minRuntime,
                              String maxRuntime, String year, String releaseDateLte, String certification) {
        gridPane = createGridPane();
        stackPane = new StackPane(gridPane);
        loadRecommendationsWithFilters(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
    }

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

    public StackPane getGrid() {
        return stackPane;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        return grid;
    }

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

    private long parseRevenue(String revenue) {
        try {
            return Long.parseLong(revenue.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            logger.warning("Failed to parse revenue: " + revenue);
            return 0;
        }
    }

    private void showError(String message) {
        Label errorLabel = new Label(message);
        gridPane.getChildren().clear();
        gridPane.add(errorLabel, 0, 0);
    }
}