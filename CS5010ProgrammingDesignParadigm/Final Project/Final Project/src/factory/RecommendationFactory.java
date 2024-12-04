package factory;

import strategy.RecommendationStrategy;
import strategy.PopularRecommendation;
import strategy.GenreRecommendation;
import strategy.RatingBasedRecommendation;

/**
 * Factory class to create recommendation strategies dynamically.
 */
public class RecommendationFactory {

    /**
     * Creates a recommendation strategy based on the given type.
     *
     * @param type The type of recommendation strategy ("popular", "genre", "rating").
     * @param additionalParam An optional parameter (e.g., genre name for genre-based strategy).
     * @return A RecommendationStrategy instance or null if the type is invalid.
     */
    public static RecommendationStrategy createStrategy(String type, String additionalParam) {
        switch (type.toLowerCase()) {
            case "popular":
                return new PopularRecommendation();
            case "genre":
                return new GenreRecommendation(additionalParam);
            case "rating":
                return new RatingBasedRecommendation();
            default:
                return null; // Return null for invalid types
        }
    }
}