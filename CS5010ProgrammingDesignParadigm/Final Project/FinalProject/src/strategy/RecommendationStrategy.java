package strategy;

import model.Movie;
import java.util.List;

/**
 *  RecommendationStrategy is the interface for different recommendation strategies.
 *  Each strategy should implement the getRecommendations method.
 *
 * @author Erdun E
 * @version 1.1
 * @since 2024-10-18
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */
public interface RecommendationStrategy {

    /**
     * Provides a list of recommended media titles.
     *
     * @return List of recommended titles.
     */
    List<String> getRecommendations();
    List<Movie> getDetailedRecommendations();
}
