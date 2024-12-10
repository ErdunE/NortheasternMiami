package strategy;

import model.Movie;
import java.util.List;

/**
 *  RecommendationStrategy is the interface for different recommendation strategies.
 *  Each strategy should implement the getDetailedRecommendations method.
 *
 * @author Erdun E
 * @version 1.2
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public interface RecommendationStrategy {

    /**
     * Provides a list of detailed movie recommendations.
     *
     * @return List of detailed Movie objects.
     */
    List<Movie> getDetailedRecommendations();
}
