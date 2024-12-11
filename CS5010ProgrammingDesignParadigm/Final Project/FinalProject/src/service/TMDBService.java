package service;

import log.LogHelper;
import model.Movie;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service class for interacting with the TMDB API.
 * Provides methods to fetch and search for movies with different criteria.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBService {

    private static final Logger logger = LogHelper.getLogger(TMDBService.class);

    private final TMDBHttpRequest tmdbHttpRequest;
    private final TMDBMovieParser tmdbMovieParser;
    private final TMDBFilterHelper tmdbFilterHelper;

    /**
     * Initializes the TMDBService with necessary components for making requests,
     * parsing responses, and filtering movies.
     */
    public TMDBService() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
        this.tmdbMovieParser = new TMDBMovieParser();
        this.tmdbFilterHelper = new TMDBFilterHelper();
    }

    /**
     * Fetches a list of popular movies.
     *
     * @return List of popular Movie objects.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public List<Movie> fetchPopularMovies() throws IOException, InterruptedException {
        logger.info("Fetching popular movies");
        JSONObject response = tmdbHttpRequest.sendGetRequest("/movie/popular");
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    /**
     * Fetches a list of movies by a specific genre.
     *
     * @param genreId The ID of the genre.
     * @return List of Movie objects matching the specified genre.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public List<Movie> fetchMoviesByGenre(int genreId) throws IOException, InterruptedException {
        logger.info("Fetching movies by genre ID: " + genreId);
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?with_genres=" + genreId);
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    /**
     * Fetches a list of high-rated movies (rating greater than or equal to 8.0).
     *
     * @return List of high-rated Movie objects.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public List<Movie> fetchRatingMovies() throws IOException, InterruptedException {
        logger.info("Fetching high-rated movies");
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?vote_average.gte=8.0");
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    /**
     * Fetches a list of movies based on various filters.
     *
     * @param genreIds       Comma-separated genre IDs.
     * @param minRating      Minimum rating value.
     * @param maxRating      Maximum rating value.
     * @param language       Language code (e.g., "en").
     * @param minRuntime     Minimum runtime in minutes.
     * @param maxRuntime     Maximum runtime in minutes.
     * @param year           Release year.
     * @param releaseDateLte Release date (less than or equal to).
     * @param certification  Certification rating (e.g., "PG-13").
     * @return List of Movie objects matching the filters.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public List<Movie> fetchMoviesWithFilters(String genreIds, String minRating, String maxRating, String language,
                                              String minRuntime, String maxRuntime, String year, String releaseDateLte,
                                              String certification) throws IOException, InterruptedException {
        logger.info("Fetching movies with filters");
        String url = tmdbFilterHelper.buildFilterUrl(genreIds, minRating, maxRating, language, minRuntime, maxRuntime, year, releaseDateLte, certification);

        JSONObject response = tmdbHttpRequest.sendGetRequest(url);
        List<Movie> movies = tmdbMovieParser.parseMoviesFromResponse(response.toString());

        movies = tmdbFilterHelper.filterMoviesByRuntime(movies, minRuntime, maxRuntime);
        movies = tmdbFilterHelper.filterMoviesByCertification(movies, certification);

        return movies;
    }

    /**
     * Searches for movies based on a keyword query.
     *
     * @param query The search query string.
     * @return List of Movie objects matching the search query.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the request is interrupted.
     */
    public List<Movie> searchMovies(String query) throws IOException, InterruptedException {
        logger.info("Searching movies with query: " + query);
        String url = "/search/movie?query=" + java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8);
        JSONObject response = tmdbHttpRequest.sendGetRequest(url);
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }
}