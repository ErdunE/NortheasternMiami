package tests;

import factory.RecommendationFactory;
import org.junit.jupiter.api.BeforeAll;
import strategy.PopularRecommendation;
import strategy.RecommendationStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationFactoryTest {

    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    @Test
    public void testCreateStrategyWithValidType() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("popular", null);
        assertNotNull(strategy, "Strategy should not be null");
        assertTrue(strategy instanceof PopularRecommendation, "Strategy should be an instance of PopularRecommendation");
    }

    @Test
    public void testCreateStrategyWithInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy("unknownType", null);
        });
        assertEquals("Unknown recommendation type: unknownType", exception.getMessage());
    }

    @Test
    public void testCreateStrategyWithNullType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy(null, null);
        });
        assertEquals("Recommendation type cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testCreateStrategyWithEmptyType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy("", null);
        });
        assertEquals("Recommendation type cannot be null or empty", exception.getMessage());
    }
}