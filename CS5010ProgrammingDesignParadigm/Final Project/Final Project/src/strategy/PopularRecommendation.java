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

    private final TMDBService tmdbService;

    public PopularRecommendation() {
        this.tmdbService = new TMDBService();
    }

    /**
     * Returns a list of popular movie recommendations.
     *
     * @return a List of popular movies examples
     */
    @Override
    public List<String> getRecommendations() {
        try {
            List<Movie> movies = tmdbService.fetchPopularMovies();
            return movies.stream()
                    .map(Movie::getTitle)
                    .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of("Failed to fetch popular movies.");
        }
    }
}
