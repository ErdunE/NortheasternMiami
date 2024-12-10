package strategy;

import model.Movie;
import service.TMDBService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import service.TMDBGenreMapper;

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

    private final TMDBService tmdbService;
    private final TMDBGenreMapper tmdbGenreMapper;
    private final int genreId;


    public GenreRecommendation(String genreName) {
        this.tmdbService = new TMDBService();
        this.tmdbGenreMapper = new TMDBGenreMapper();
        this.genreId = tmdbGenreMapper.getIdByGenreName(genreName);
    }

    @Override
    public List<String> getRecommendations() {
        try {
            List<Movie> movies = tmdbService.fetchMoviesByGenre(genreId);
            return movies.stream()
                    .map(Movie::getTitle)
                    .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching movies for genre ID: " + genreId);
            e.printStackTrace();
            return List.of("An error occurred while fetching movies. Please try again later.");
        }
    }


    @Override
    public List<Movie> getDetailedRecommendations() {
        try {
            return tmdbService.fetchMoviesByGenre(genreId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
