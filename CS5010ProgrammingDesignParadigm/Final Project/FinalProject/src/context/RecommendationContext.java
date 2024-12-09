package context;

import model.Movie;
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

    private RecommendationStrategy currentStrategy;

    public void setRecommendationStrategy(RecommendationStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public List<String> getRecommendations() {
        if (currentStrategy != null) {
            return currentStrategy.getRecommendations();
        } else {
            return List.of("No strategy set.");
        }
    }

    public List<Movie> getRecommendationsWithDetails() {
        if (currentStrategy != null) {
            return currentStrategy.getDetailedRecommendations();
        } else {
            return List.of();
        }
    }
}
