package tests;

import factory.RecommendationFactory;
import strategy.RecommendationStrategy;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;
import strategy.RatingBasedRecommendation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RecommendationFactory class.
 */
public class FactoryTest {

    @Test
    public void testCreatePopularStrategy() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("popular", null);
        assertNotNull(strategy);
        assertTrue(strategy instanceof PopularRecommendation);
    }

    @Test
    public void testCreateGenreStrategy() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("genre", "Action");
        assertNotNull(strategy);
        assertTrue(strategy instanceof GenreRecommendation);
    }

    @Test
    public void testCreateRatingStrategy() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("rating", null);
        assertNotNull(strategy);
        assertTrue(strategy instanceof RatingBasedRecommendation);
    }

    @Test
    public void testInvalidStrategyType() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("invalid", null);
        assertNull(strategy);
    }
}