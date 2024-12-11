package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Strategy for recommending popular movies.
 * Uses the TMDB API to fetch a list of popular movies.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class PopularRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(PopularRecommendation.class);
    private final TMDBService tmdbService = new TMDBService();

    /**
     * Provides a list of detailed movie recommendations based on popularity.
     *
     * @return List of popular Movie objects.
     */
    @Override
    public List<Movie> getDetailedRecommendations() {
        logger.info("Fetching popular movies.");
        try {
            return tmdbService.fetchPopularMovies();
        } catch (IOException | InterruptedException e) {
            logger.severe("Failed to fetch popular movies: " + e.getMessage());
            return List.of();
        }
    }
}