package strategy;

import log.LogHelper;
import model.Movie;
import service.TMDBService;
import service.TMDBGenreMapper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * GenreRecommendation class provides genre-specific movie recommendations.
 *
 * @author Erdun E
 * @version 1.3
 * @since 2024-12-07
 * Course: CS5010 Program Design Paradigm
 * Program: Mid-Semester Assignment
 */
public class GenreRecommendation implements RecommendationStrategy {

    private static final Logger logger = LogHelper.getLogger(GenreRecommendation.class);
    private final TMDBService tmdbService;
    private final int genreId;

    public GenreRecommendation(String genreName) {
        this.tmdbService = new TMDBService();
        TMDBGenreMapper tmdbGenreMapper = new TMDBGenreMapper();
        this.genreId = tmdbGenreMapper.getIdByGenreName(genreName);
        logger.info("GenreRecommendation initialized with genre ID: " + genreId);
    }

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
