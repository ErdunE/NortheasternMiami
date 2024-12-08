package service;

import model.Movie;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

/**
 * Service class for interacting with the TMDB API.
 */
public class TMDBService {

    private final TMDBHttpRequest tmdbHttpRequest;
    private final TMDBMovieParser tmdbMovieParser;

    public TMDBService() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
        this.tmdbMovieParser = new TMDBMovieParser();
    }

    public List<Movie> fetchPopularMovies() throws IOException, InterruptedException {
        JSONObject response = tmdbHttpRequest.sendGetRequest("/movie/popular");
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }

    public List<Movie> fetchMoviesByGenre(int genreId) throws IOException, InterruptedException {
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?with_genres=" + genreId);
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }
}