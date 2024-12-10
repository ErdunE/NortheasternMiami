package factory;

import log.LogHelper;
import strategy.RecommendationStrategy;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;
import strategy.RatingBasedRecommendation;

import java.util.logging.Logger;

/**
 * Factory class to create recommendation strategies dynamically.
 */
public class RecommendationFactory {

    private static final Logger logger = LogHelper.getLogger(RecommendationFactory.class);

    public static RecommendationStrategy createStrategy(String type, String additionalParam) {
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