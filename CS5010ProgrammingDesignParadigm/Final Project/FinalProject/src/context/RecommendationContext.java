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
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RecommendationContext {

    private static final Logger logger = LogHelper.getLogger(RecommendationContext.class);

    private RecommendationStrategy currentStrategy;

    /**
     * Sets the current recommendation strategy.
     *
     * @param strategy The recommendation strategy to be used.
     * @throws IllegalArgumentException if the strategy is null.
     */
    public void setRecommendationStrategy(RecommendationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Recommendation strategy cannot be null");
        }
        this.currentStrategy = strategy;
        logger.info("Recommendation strategy set to: " + strategy.getClass().getSimpleName());
    }

    /**
     * Provides a list of detailed movie recommendations using the current strategy.
     *
     * @return List of detailed Movie objects.
     * @throws IllegalStateException if no recommendation strategy is set.
     */
    public List<Movie> getRecommendationsWithDetails() {
        if (currentStrategy == null) {
            logger.warning("No recommendation strategy set. Throwing IllegalStateException.");
            throw new IllegalStateException("Recommendation strategy is not set");
        }

        logger.info("Fetching detailed recommendations from strategy: " + currentStrategy.getClass().getSimpleName());
        return currentStrategy.getDetailedRecommendations();
    }
}
