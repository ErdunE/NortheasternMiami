package factory;

import log.LogHelper;
import strategy.RecommendationStrategy;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;
import strategy.RatingBasedRecommendation;

import java.util.logging.Logger;

/**
 * Factory class to create different types of recommendation strategies.
 * Provides strategies based on the given type.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class RecommendationFactory {

    private static final Logger logger = LogHelper.getLogger(RecommendationFactory.class);

    /**
     * Creates a recommendation strategy based on the given type.
     *
     * @param type The type of recommendation strategy to create. Supported types are popular, genre(deprecated), rating.
     * @param additionalParam Additional parameter required for some strategies, like genre-based recommendations.
     * @return A {@link RecommendationStrategy} based on the specified type.
     * @throws IllegalArgumentException if the type is null, empty, or not supported.
     */
    public static RecommendationStrategy createStrategy(String type, String additionalParam) {
        if (type == null || type.trim().isEmpty()) {
            logger.severe("Recommendation type cannot be null or empty");
            throw new IllegalArgumentException("Recommendation type cannot be null or empty");
        }

        switch (type.toLowerCase()) {
            case "popular":
                logger.info("Creating PopularRecommendation strategy.");
                return new PopularRecommendation();
            case "genre":
                logger.info("Creating GenreRecommendation strategy with parameter: " + additionalParam);
                return new GenreRecommendation(additionalParam);
            case "rating":
                logger.info("Creating RatingBasedRecommendation strategy.");
                return new RatingBasedRecommendation();
            default:
                logger.severe("Unknown recommendation type: " + type);
                throw new IllegalArgumentException("Unknown recommendation type: " + type);
        }
    }
}