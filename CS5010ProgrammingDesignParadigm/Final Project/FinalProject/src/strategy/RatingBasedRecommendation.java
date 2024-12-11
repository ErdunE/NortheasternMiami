package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Strategy for recommending high-rated movies.
 * Uses the TMDB API to fetch and sort a list of high-rated movies.
 * The movies are sorted by rating in descending order.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RatingBasedRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(RatingBasedRecommendation.class);
    private final TMDBService tmdbService;

    /**
     * Initializes the RatingBasedRecommendation strategy and TMDBService.
     */
    public RatingBasedRecommendation() {
        this.tmdbService = new TMDBService();
        logger.info("RatingBasedRecommendation initialized.");
    }

    /**
     * Provides a list of detailed movie recommendations based on high ratings.
     * Fetches high-rated movies, sorts them by rating in descending order,
     * and returns the top 20 movies.
     *
     * @return List of high-rated Movie objects.
     */
    @Override
    public List<Movie> getDetailedRecommendations() {
        logger.info("Fetching high-rated movies for RatingBasedRecommendation strategy.");
        try {
            List<Movie> movies = tmdbService.fetchRatingMovies().stream()
                    .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                    .limit(20)
                    .toList();
            logger.info("Successfully fetched and sorted high-rated movies.");
            return movies;
        } catch (IOException | InterruptedException e) {
            logger.severe("Failed to fetch high-rated movies: " + e.getMessage());
            return List.of();
        }
    }
}