package tests;

import factory.RecommendationFactory;
import org.junit.jupiter.api.BeforeAll;
import strategy.PopularRecommendation;
import strategy.RecommendationStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for RecommendationFactory.
 * Verifies the creation of different recommendation strategies and handles invalid inputs.
 * Also tests exception handling for invalid or null strategy types.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RecommendationFactoryTest {

    /**
     * Disables logging before running the tests.
     */
    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    /**
     * Tests the creation of a valid recommendation strategy.
     * Verifies that a PopularRecommendation instance is created.
     */
    @Test
    public void testCreateStrategyWithValidType() {
        RecommendationStrategy strategy = RecommendationFactory.createStrategy("popular", null);
        assertNotNull(strategy, "Strategy should not be null");
        assertTrue(strategy instanceof PopularRecommendation, "Strategy should be an instance of PopularRecommendation");
    }

    /**
     * Tests the creation of a strategy with an invalid type.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testCreateStrategyWithInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy("unknownType", null);
        });
        assertEquals("Unknown recommendation type: unknownType", exception.getMessage());
    }

    /**
     * Tests the creation of a strategy with a null type.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testCreateStrategyWithNullType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy(null, null);
        });
        assertEquals("Recommendation type cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the creation of a strategy with an empty type.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testCreateStrategyWithEmptyType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RecommendationFactory.createStrategy("", null);
        });
        assertEquals("Recommendation type cannot be null or empty", exception.getMessage());
    }
}