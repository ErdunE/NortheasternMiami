package factory;

import strategy.RecommendationStrategy;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;
import strategy.RatingBasedRecommendation;

/**
 * Factory class to create recommendation strategies dynamically.
 */
public class RecommendationFactory {

    public static RecommendationStrategy createStrategy(String type, String additionalParam) {
        switch (type.toLowerCase()) {
            case "popular":
                return new PopularRecommendation();
            case "genre":
                return new GenreRecommendation(additionalParam);
            case "rating":
                return new RatingBasedRecommendation();
            default:
                return null;
        }
    }
}