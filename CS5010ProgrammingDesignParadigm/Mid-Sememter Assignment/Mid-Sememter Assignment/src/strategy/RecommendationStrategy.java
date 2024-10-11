package strategy;

import java.util.List;

/**
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-01
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 *
 * RecommendationStrategy is the interface for different recommendation strategies.
 * Each strategy should implement the getRecommendations method.
 */

public interface RecommendationStrategy {

    /**
     * Provides a list of recommended media titles.
     * @return List of recommended titles.
     */
    List<String> getRecommendations();
}
