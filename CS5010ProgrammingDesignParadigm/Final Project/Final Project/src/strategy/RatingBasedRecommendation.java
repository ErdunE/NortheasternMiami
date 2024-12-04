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
            new Movie("Inception", "Action", 9.0),
            new Movie("The Dark Knight", "Action", 9.2),
            new Movie("Interstellar", "Sci-Fi", 8.8),
            new Movie("Forrest Gump", "Drama", 8.9),
            new Movie("The Shawshank Redemption", "Drama", 9.3)
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