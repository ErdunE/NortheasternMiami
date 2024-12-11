package tests;

import context.RecommendationContext;
import factory.RecommendationFactory;
import strategy.RecommendationStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationContextTest {

    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    @Test
    public void testSetAndGetRecommendationStrategy() {
        RecommendationContext context = new RecommendationContext();
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("popular", null);

        assertNotNull(strategy, "Strategy should not be null");

        context.setRecommendationStrategy(strategy);

        assertNotNull(context.getRecommendationsWithDetails(), "Recommendations should not be null");

        assertFalse(context.getRecommendationsWithDetails().isEmpty(), "Recommendations list should not be empty");
    }

    @Test
    public void testSetAndGetDifferentRecommendationStrategy() {
        RecommendationContext context = new RecommendationContext();
        RecommendationStrategy popularStrategy = RecommendationFactory.createStrategy("popular", null);
        RecommendationStrategy recentStrategy = RecommendationFactory.createStrategy("rating", null);

        assertNotNull(popularStrategy, "Popular strategy should not be null");
        assertNotNull(recentStrategy, "Rating strategy should not be null");

        context.setRecommendationStrategy(popularStrategy);
        assertNotNull(context.getRecommendationsWithDetails(), "Popular recommendations should not be null");

        context.setRecommendationStrategy(recentStrategy);
        assertNotNull(context.getRecommendationsWithDetails(), "Recent recommendations should not be null");
    }

    @Test
    public void testGetRecommendationsWithoutSettingStrategy() {
        RecommendationContext context = new RecommendationContext();

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            context.getRecommendationsWithDetails();
        }, "Should throw IllegalStateException if strategy is not set");

        assertEquals("Recommendation strategy is not set", exception.getMessage());
    }

    @Test
    public void testSetNullRecommendationStrategy() {
        RecommendationContext context = new RecommendationContext();

        assertThrows(IllegalArgumentException.class, () -> {
            context.setRecommendationStrategy(null);
        }, "Should throw IllegalArgumentException when setting a null strategy");
    }
}