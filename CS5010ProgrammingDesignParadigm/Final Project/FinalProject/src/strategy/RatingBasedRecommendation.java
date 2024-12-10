package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * RatingBasedRecommendation class provides movie recommendations based on ratings.
 */
public class RatingBasedRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(RatingBasedRecommendation.class);
    private final TMDBService tmdbService;

    public RatingBasedRecommendation() {
        this.tmdbService = new TMDBService();
        logger.info("RatingBasedRecommendation initialized.");
    }

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