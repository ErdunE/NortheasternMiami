package strategy;

import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PopularRecommendation class provides popular movie recommendations.
 *
 * @author Erdun E
 * @version 1.2
 * @since 2024-12-04
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */

public class PopularRecommendation implements RecommendationStrategy {

    private final TMDBService tmdbService = new TMDBService();

    /**
     * Returns a list of popular movie recommendations.
     *
     * @return a List of popular movies examples
     */
    @Override
    public List<String> getRecommendations() {
        try {
            return tmdbService.fetchPopularMovies()
                    .stream()
                    .map(Movie::getTitle)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Failed to fetch popular recommendations.");
        }
    }

    @Override
    public List<Movie> getDetailedRecommendations() {
        try {
            return tmdbService.fetchPopularMovies();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
