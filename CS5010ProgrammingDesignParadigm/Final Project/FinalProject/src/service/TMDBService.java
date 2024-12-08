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

    public List<Movie> fetchMoviesWithFilters(String genreIds, String minRating, String maxRating, String language, String minRuntime, String maxRuntime, String year) throws IOException, InterruptedException {
        StringBuilder url = new StringBuilder("/discover/movie?");

        if (genreIds != null && !genreIds.isEmpty()) {
            url.append("with_genres=").append(genreIds).append("&");
        }
        if (minRating != null && !minRating.isEmpty()) url.append("vote_average.gte=").append(minRating).append("&");
        if (maxRating != null && !maxRating.isEmpty()) url.append("vote_average.lte=").append(maxRating).append("&");
        if (language != null && !language.isEmpty()) url.append("with_original_language=").append(language).append("&");
        if (minRuntime != null && !minRuntime.isEmpty()) url.append("with_runtime.gte=").append(minRuntime).append("&");
        if (maxRuntime != null && !maxRuntime.isEmpty()) url.append("with_runtime.lte=").append(maxRuntime).append("&");
        if (year != null && !year.isEmpty()) url.append("primary_release_year=").append(year).append("&");

        if (url.charAt(url.length() - 1) == '&') {
            url.deleteCharAt(url.length() - 1);
        }

        JSONObject response = tmdbHttpRequest.sendGetRequest(url.toString());
        return tmdbMovieParser.parseMoviesFromResponse(response.toString());
    }
}