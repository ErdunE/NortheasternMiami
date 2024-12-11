package tests;

import model.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.TMDBService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TMDBService.
 * Verifies different methods for fetching movies from the TMDB API.
 * Tests popular movies, genre-based movies, high-rated movies, filtered movies, and movie search.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBServiceTest {

    /**
     * Disables logging before running the tests.
     */
    @BeforeAll
    public static void disableLogging() {
        System.setProperty("disableLogging", "true");
    }

    /**
     * Tests fetching popular movies.
     * Verifies that the returned movie list is not null or empty.
     */
    @Test
    public void testFetchPopularMovies() {
        TMDBService service = new TMDBService();

        try {
            List<Movie> movies = service.fetchPopularMovies();
            assertNotNull(movies, "Movies list should not be null");
            assertFalse(movies.isEmpty(), "Movies list should not be empty");
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during fetchPopularMovies: " + e.getMessage());
        }
    }

    /**
     * Tests fetching movies by genre.
     * Verifies that the returned movie list is not null or empty.
     */
    @Test
    public void testFetchMoviesByGenre() {
        TMDBService service = new TMDBService();

        try {
            int genreId = 28;
            List<Movie> movies = service.fetchMoviesByGenre(genreId);
            assertNotNull(movies, "Movies list should not be null");
            assertFalse(movies.isEmpty(), "Movies list should not be empty");
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during fetchMoviesByGenre: " + e.getMessage());
        }
    }

    /**
     * Tests fetching high-rated movies.
     * Verifies that the returned movie list is not null or empty.
     */
    @Test
    public void testFetchRatingMovies() {
        TMDBService service = new TMDBService();

        try {
            List<Movie> movies = service.fetchRatingMovies();
            assertNotNull(movies, "Movies list should not be null");
            assertFalse(movies.isEmpty(), "Movies list should not be empty");
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during fetchRatingMovies: " + e.getMessage());
        }
    }

    /**
     * Tests fetching movies with various filters.
     * Verifies that the returned movie list is not null or empty.
     */
    @Test
    public void testFetchMoviesWithFilters() {
        TMDBService service = new TMDBService();

        try {
            List<Movie> movies = service.fetchMoviesWithFilters(
                    "28,12", // genreIds: Action, Adventure
                    "7.0",   // minRating
                    "9.0",   // maxRating
                    "en",    // language
                    "90",    // minRuntime
                    "180",   // maxRuntime
                    "2022",  // year
                    "2023-12-31", // releaseDateLte
                    "PG-13"  // certification
            );

            assertNotNull(movies, "Movies list should not be null");
            assertFalse(movies.isEmpty(), "Movies list should not be empty");
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during fetchMoviesWithFilters: " + e.getMessage());
        }
    }

    /**
     * Tests searching for movies by a query.
     * Verifies that the returned movie list is not null or empty.
     */
    @Test
    public void testSearchMovies() {
        TMDBService service = new TMDBService();

        try {
            String query = "Inception";
            List<Movie> movies = service.searchMovies(query);
            assertNotNull(movies, "Movies list should not be null");
            assertFalse(movies.isEmpty(), "Movies list should not be empty");
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during searchMovies: " + e.getMessage());
        }
    }
}