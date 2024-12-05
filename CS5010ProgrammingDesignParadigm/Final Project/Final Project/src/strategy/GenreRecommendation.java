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


    public GenreRecommendation(String genreName) {
        this.tmdbService = new TMDBService();
        this.genreId = tmdbService.getGenreIdByName(genreName); // 使用 TMDBService 获取 genreId
    }

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
