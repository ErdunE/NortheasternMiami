package context;

import log.LogHelper;
import model.Movie;
import strategy.RecommendationStrategy;

import java.util.List;
import java.util.logging.Logger;

/**
 * Context class to manage different recommendation strategies.
 * Allows switching between strategies dynamically.
 *
 * @author Erdun E
 * @version 1.1
 * @since 2024-10-18
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */
public class RecommendationContext {

    private static final Logger logger = LogHelper.getLogger(RecommendationContext.class);

    private RecommendationStrategy currentStrategy;

    public void setRecommendationStrategy(RecommendationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Recommendation strategy cannot be null");
        }
        this.currentStrategy = strategy;
        logger.info("Recommendation strategy set to: " + strategy.getClass().getSimpleName());
    }

    public List<Movie> getRecommendationsWithDetails() {
        if (currentStrategy == null) {
            logger.warning("No recommendation strategy set. Throwing IllegalStateException.");
            throw new IllegalStateException("Recommendation strategy is not set");
        }

        logger.info("Fetching detailed recommendations from strategy: " + currentStrategy.getClass().getSimpleName());
        return currentStrategy.getDetailedRecommendations();
    }
}
