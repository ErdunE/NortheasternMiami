package tests;

import strategy.PopularRecommendation;
import model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class for PopularRecommendation strategy.
 * Verifies the initialization and behavior of the PopularRecommendation class.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class PopularRecommendationTest {

    /**
     * Disables logging before running the tests.
     */
    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    /**
     * Tests the initialization of PopularRecommendation.
     * Ensures that the PopularRecommendation object is created successfully.
     */
    @Test
    public void testPopularRecommendationInitialization() {
        PopularRecommendation recommendation = new PopularRecommendation();
        assertNotNull(recommendation, "PopularRecommendation should be initialized successfully");
    }

    /**
     * Tests the getDetailedRecommendations method.
     * Ensures that the method returns a non-null list of movie recommendations.
     */
    @Test
    public void testGetDetailedRecommendations() {
        PopularRecommendation recommendation = new PopularRecommendation();
        List<Movie> detailedRecommendations = recommendation.getDetailedRecommendations();
        assertNotNull(detailedRecommendations, "Detailed recommendations list should not be null");
    }
}