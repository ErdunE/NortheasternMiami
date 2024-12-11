package tests;

import strategy.RatingBasedRecommendation;
import model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class for RatingBasedRecommendation strategy.
 * Verifies the initialization and behavior of the RatingBasedRecommendation class.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RatingBasedRecommendationTest {

    /**
     * Disables logging before running the tests.
     */
    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    /**
     * Tests the initialization of RatingBasedRecommendation.
     * Ensures that the RatingBasedRecommendation object is created successfully.
     */
    @Test
    public void testRatingBasedRecommendationInitialization() {
        RatingBasedRecommendation recommendation = new RatingBasedRecommendation();
        assertNotNull(recommendation, "RatingBasedRecommendation should be initialized successfully");
    }

    /**
     * Tests the getDetailedRecommendations method.
     * Ensures that the method returns a non-null list of movie recommendations.
     */
    @Test
    public void testGetDetailedRecommendations() {
        RatingBasedRecommendation recommendation = new RatingBasedRecommendation();
        List<Movie> detailedRecommendations = recommendation.getDetailedRecommendations();
        assertNotNull(detailedRecommendations, "Detailed recommendations list should not be null");
    }
}