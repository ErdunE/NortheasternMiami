package service;

import log.LogHelper;
import model.Movie;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service class for interacting with the TMDB API.
 */
public class TMDBService {

    private static final Logger logger = LogHelper.getLogger(TMDBService.class);

    private final TMDBHttpRequest tmdbHttpRequest;
    private final TMDBMovieParser tmdbMovieParser;
    private final TMDBFilterHelper tmdbFilterHelper;

    public TMDBService() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
        this.tmdbMovieParser = new TMDBMovieParser();
        this.tmdbFilterHelper = new TMDBFilterHelper();
    }

    public List<Movie> fetchPopularMovies() throws IOException, InterruptedException {
        logger.info("Fetching popular movies");
        JSONObject response = tmdbHttpRequest.sendGetRequest("/movie/popular");
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    public List<Movie> fetchMoviesByGenre(int genreId) throws IOException, InterruptedException {
        logger.info("Fetching movies by genre ID: " + genreId);
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?with_genres=" + genreId);
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    public List<Movie> fetchRatingMovies() throws IOException, InterruptedException {
        logger.info("Fetching high-rated movies");
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?vote_average.gte=8.0");
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

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

    public List<Movie> searchMovies(String query) throws IOException, InterruptedException {
        logger.info("Searching movies with query: " + query);
        String url = "/search/movie?query=" + java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8);
        JSONObject response = tmdbHttpRequest.sendGetRequest(url);
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }
}