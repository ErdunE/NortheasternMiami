package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;
import service.TMDBGenreMapper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Genre is deprecated because filter function can cover its feature.
 *
 * Strategy for recommending movies based on a specific genre.
 * Uses the TMDB API to fetch movies of the specified genre.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class GenreRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(GenreRecommendation.class);
    private final TMDBService tmdbService;
    private final int genreId;

    /**
     * Initializes the GenreRecommendation with a genre name.
     * The genre name is mapped to its corresponding ID.
     *
     * @param genreName The name of the genre.
     */
    public GenreRecommendation(String genreName) {
        this.tmdbService = new TMDBService();
        TMDBGenreMapper tmdbGenreMapper = new TMDBGenreMapper();
        this.genreId = tmdbGenreMapper.getIdByGenreName(genreName);
        logger.info("GenreRecommendation initialized with genre ID: " + genreId);
    }

    /**
     * Provides a list of detailed movie recommendations based on the genre.
     *
     * @return List of Movie objects belonging to the specified genre.
     */
    @Override
    public List<Movie> getDetailedRecommendations() {
        logger.info("Fetching movies for genre ID: " + genreId);
        try {
            return tmdbService.fetchMoviesByGenre(genreId);
        } catch (IOException | InterruptedException e) {
            logger.severe("Failed to fetch movies for genre ID " + genreId + ": " + e.getMessage());
            return List.of();
        }
    }
}
