package context;

import strategy.RecommendationStrategy;
import java.util.List;

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

    private RecommendationStrategy strategy;

    /**
     * Set the current recommendation strategy.
     *
     * @param strategy The strategy to be applied.
     */
    public void setRecommendationStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Get recommendations from the current strategy.
     *
     * @return List of recommended media titles.
     */
    public List<String> getRecommendations(){
        return strategy.getRecommendations();
    }
}
