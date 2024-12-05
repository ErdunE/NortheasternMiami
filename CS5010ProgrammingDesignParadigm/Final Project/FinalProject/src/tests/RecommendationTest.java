package tests;

import context.RecommendationContext;
import org.junit.Test;
import strategy.GenreRecommendation;
import strategy.PopularRecommendation;
import strategy.RatingBasedRecommendation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the entertainment recommendation system.
 *
 * @author Erdun E
 * @version 1.1
 * @since 2024-10-18
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
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

    @Test
    public void testRatingBasedRecommendation() {
        RecommendationContext context = new RecommendationContext();
        context.setRecommendationStrategy(new RatingBasedRecommendation());
        List<String> recommendations = context.getRecommendations();

        assertNotNull(recommendations);
        assertEquals(3, recommendations.size());
        assertTrue(recommendations.contains("The Shawshank Redemption"));
    }
}
