package strategy;

import java.util.Arrays;
import java.util.List;

/**
 * PopularRecommendation class provides popular movie recommendations.
 *
 * @author Erdun E
 * @version 1.1
 * @since 2024-10-18
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */

public class PopularRecommendation implements RecommendationStrategy {

    /**
     * Returns a list of popular movie recommendations.
     *
     * @return a List of popular movies examples
     */
    @Override
    public List<String> getRecommendations() {
        return Arrays.asList("Inception", "The Dark Knight", "Interstellar");
    }
}
