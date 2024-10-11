package strategy;

import java.util.Arrays;
import java.util.List;

/**
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-01
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 *
 * GenreRecommendation class provides genre-specific movie recommendations.
 */


public class GenreRecommendation implements RecommendationStrategy {
    private String genre;

    /**
     * Constructor to set the desired genre.
     * @param genre The genre of movies to recommend.
     */
    public GenreRecommendation(String genre) {
        this.genre = genre;
    }

    /**
     * Based on user choice return recommendation
     */
    @Override
    public List<String> getRecommendations() {
        if(genre.equalsIgnoreCase("Action")){
            return Arrays.asList("Mad Max", "Die Hard", "John Wick");
        } else if(genre.equalsIgnoreCase("Drama")){
            return Arrays.asList("The Shaw shank Redemption", "Forrest Gump");
        } else{
            return Arrays.asList("No recommendations for this genre.");
        }
    }
}
