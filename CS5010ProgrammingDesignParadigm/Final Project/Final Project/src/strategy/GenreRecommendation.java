package strategy;

import model.Movie;
import service.TMDBService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GenreRecommendation class provides genre-specific movie recommendations.
 *
 * @author Erdun E
 * @version 1.2
 * @since 2024-12-04
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */
public class GenreRecommendation implements RecommendationStrategy {

    private final TMDBService tmdbService;
    private final int genreId;
    private String genre;

    public GenreRecommendation(int genreId) {
        this.tmdbService = new TMDBService();
        this.genreId = genreId;
    }

    /**
     * Constructor to set the desired genre.
     *
     * @param genre The genre of movies to recommend.
     */
    public GenreRecommendation(String genre) {
        this.tmdbService = new TMDBService();
        this.genre = genre;

        // Map genre string to TMDB genre ID
        this.genreId = mapGenreToId(genre);
    }

    /**
     * Returns a list of movie recommendations based on the specified genre.
     *
     * @return a List of movies for the specified genre, or a message display that no recommendations.
     */
    @Override
    public List<String> getRecommendations() {
        try {
            List<Movie> movies = tmdbService.fetchMoviesByGenre(genreId);
            return movies.stream()
                    .map(Movie::getTitle)
                    .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of("Failed to fetch movies for the genre.");
        }
    }

    /**
     * Maps a genre name to a TMDB genre ID.
     *
     * @param genre The genre name.
     * @return The corresponding genre ID.
     */
    private int mapGenreToId(String genre) {
        switch (genre.toLowerCase()) {
            case "action":
                return 28;
            case "comedy":
                return 35;
            case "drama":
                return 18;
            case "horror":
                return 27;
            default:
                return 0; // Default or unknown genre
        }
    }
}
