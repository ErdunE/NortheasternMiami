package strategy;

import java.util.Arrays;
import java.util.List;

/**
 * GenreRecommendation class provides genre-specific movie recommendations.
 *
 * @author Erdun E
 * @version 1.1
 * @since 2024-10-18
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */


public class GenreRecommendation implements RecommendationStrategy {
    private String genre;

    /**
     * Constructor to set the desired genre.
     *
     * @param genre The genre of movies to recommend.
     */
    public GenreRecommendation(String genre) {
        this.genre = genre;
    }

    /**
     * Returns a list of movie recommendations based on the specified genre.
     *
     * @return a List of movies for the specified genre, or a message display that no recommendations.
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
