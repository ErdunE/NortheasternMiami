package strategy;

import java.util.Arrays;
import java.util.List;

/**
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-01
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 *
 * PopularRecommendation class provides popular movie recommendations.
 */

public class PopularRecommendation implements RecommendationStrategy {

    @Override
    public List<String> getRecommendations() {
        return Arrays.asList("Inception", "The Dark Knight", "Interstellar");
    }
}
