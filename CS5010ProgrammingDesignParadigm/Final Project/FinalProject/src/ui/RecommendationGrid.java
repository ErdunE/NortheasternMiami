package ui;

import context.RecommendationContext;
import factory.RecommendationFactory;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Movie;
import service.TMDBService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationGrid {

    private GridPane gridPane;
    private StackPane stackPane;
    private List<Movie> recommendations;
    private final Map<String, Boolean> sortOrder = new HashMap<>();
    private final LoadingSpinner loadingSpinner = new LoadingSpinner();

    public RecommendationGrid(String type, String additionalParam) {
        initializeGrid();
        loadRecommendations(type, additionalParam);
    }

    public RecommendationGrid(String genreIds, String minRating, String maxRating, String language, String minRuntime, String maxRuntime, String year, String releaseDateLte, String certification) {
        initializeGrid();
        loadRecommendationsWithFilters(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
    }

    public RecommendationGrid(List<Movie> movies) {
        initializeGrid();
        this.recommendations = movies;
        displayMovies(recommendations);
    }

    public StackPane getGrid() {
        return stackPane;
    }

    private void initializeGrid() {
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));

        stackPane = new StackPane(gridPane);
    }

    private void loadRecommendations(String type, String additionalParam) {
        loadingSpinner.show(stackPane);

        new Thread(() -> {
            RecommendationContext context = new RecommendationContext();
            context.setRecommendationStrategy(RecommendationFactory.createStrategy(type, additionalParam));
            recommendations = context.getRecommendationsWithDetails();

            Platform.runLater(() -> {
                displayMovies(recommendations);
                loadingSpinner.hide(stackPane);
            });
        }).start();
    }

    private void loadRecommendationsWithFilters(String genreIds, String minRating, String maxRating, String language, String minRuntime, String maxRuntime, String year, String releaseDateLte, String certification) {
        loadingSpinner.show(stackPane);

        new Thread(() -> {
            TMDBService tmdbService = new TMDBService();
            try {
                recommendations = tmdbService.fetchMoviesWithFilters(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);
                Platform.runLater(() -> {
                    displayMovies(recommendations);
                    loadingSpinner.hide(stackPane);
                });
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Platform.runLater(() -> loadingSpinner.hide(stackPane));
            }
        }).start();
    }

    public void sortMovies(String criteria) {
        boolean ascending = sortOrder.getOrDefault(criteria, false);

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
        }

        sortOrder.put(criteria, !ascending);
        displayMovies(recommendations);
    }

    private void displayMovies(List<Movie> recommendations) {
        gridPane.getChildren().clear();

        if (recommendations == null || recommendations.isEmpty()) {
            Label noResultsLabel = new Label("No movies match your filters. Please try different criteria.");
            gridPane.getChildren().add(noResultsLabel);
            return;
        }

        int col = 0, row = 0;
        for (Movie movie : recommendations) {
            MovieCard movieCard = new MovieCard(movie);

            movieCard.getMovieCard().setOnMouseClicked(e -> {
                System.out.println("Direct GridPane card click! Movie: " + movie.getTitle());
                MovieDetailsWindow.display(movie);
            });

            gridPane.add(movieCard.getMovieCard(), col++, row);

            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }

    private long parseRevenue(String revenue) {
        try {
            return Long.parseLong(revenue.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}