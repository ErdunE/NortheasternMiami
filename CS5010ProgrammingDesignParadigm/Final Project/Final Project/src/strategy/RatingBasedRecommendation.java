package strategy;

import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RatingBasedRecommendation implements RecommendationStrategy {

    private final TMDBService tmdbService;

    public RatingBasedRecommendation() {
        this.tmdbService = new TMDBService();
    }

    @Override
    public List<String> getRecommendations() {
        try {
            // Fetch popular movies and sort by rating
            List<Movie> movies = tmdbService.fetchPopularMovies().stream()
                    .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .limit(8)
                    .collect(Collectors.toList());

            // Return movie titles
            return movies.stream()
                    .map(Movie::getTitle)
                    .collect(Collectors.toList());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of("Failed to fetch high-rated movies.");
        }
    }

    @Override
    public List<Movie> getDetailedRecommendations() {
        try {
            // Fetch popular movies and sort by rating
            return tmdbService.fetchPopularMovies().stream()
                    .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .limit(8)
                    .collect(Collectors.toList());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}