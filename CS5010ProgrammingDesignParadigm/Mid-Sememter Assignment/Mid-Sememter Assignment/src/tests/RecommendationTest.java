package tests;

import context.RecommendationContext;
import org.junit.Test;
import strategy.GenreRecommendation;
import strategy.PopularRecommendation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-01
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 *
 * Unit tests for the entertainment recommendation system.
 */
public class RecommendationTest {

    /**
     * Test Popular Recommendation
     */
    @Test
    public void testPopularRecommendation() {
        RecommendationContext context = new RecommendationContext();
        context.setRecommendationStrategy(new PopularRecommendation());
        List<String> recommendations = context.getRecommendations();

        assertNotNull(recommendations);
        assertTrue(recommendations.contains("Inception"));
    }

    /**
     * Test Genre Recommendation
     */
    @Test
    public void testGenreRecommendation() {
        RecommendationContext context = new RecommendationContext();
        context.setRecommendationStrategy(new GenreRecommendation("Action"));
        List<String> recommendations = context.getRecommendations();

        assertNotNull(recommendations);
        assertTrue(recommendations.contains("Mad Max"));
    }

    /**
     * Test Unknown Genre Recommendation
     */
    @Test
    public void testUnknownGenreRecommendation() {
        RecommendationContext context = new RecommendationContext();
        context.setRecommendationStrategy(new GenreRecommendation("I-Love-Movie"));
        List<String> recommendations = context.getRecommendations();

        assertNotNull(recommendations);
        assertEquals(1, recommendations.size());
        assertEquals("No recommendations for this genre.", recommendations.get(0));
    }
}
