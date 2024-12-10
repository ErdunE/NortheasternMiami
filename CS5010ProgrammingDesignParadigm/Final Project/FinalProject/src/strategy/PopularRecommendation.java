package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * PopularRecommendation class provides popular movie recommendations.
 */
public class PopularRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(PopularRecommendation.class);
    private final TMDBService tmdbService = new TMDBService();

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