package strategy;

import model.Movie;

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Provides recommendations based on movie ratings.
 */
public class RatingBasedRecommendation implements RecommendationStrategy {

    // Simulated data: Movie titles with ratings
    private final List<Movie> movies = Arrays.asList(
            new Movie("Inception", Arrays.asList("Action", "Sci-Fi"), 9.0, "A mind-bending thriller", null),
            new Movie("The Dark Knight", Arrays.asList("Action", "Crime"), 9.2, "A gritty crime drama", null),
            new Movie("Interstellar", Arrays.asList("Sci-Fi", "Adventure"), 8.8, "An epic journey through space", null),
            new Movie("Forrest Gump", Arrays.asList("Drama", "Romance"), 8.9, "A heartwarming tale", null),
            new Movie("The Shawshank Redemption", Arrays.asList("Drama", "Crime"), 9.3, "A story of hope and friendship", null)
    );

    @Override
    public List<String> getRecommendations() {
        // Sort movies by rating in descending order and return top 3 titles
        return movies.stream()
                .sorted(Comparator.comparingDouble(Movie::getRating).reversed())
                .limit(3)
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}