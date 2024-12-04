package service;

import model.Movie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Service class for interacting with the TMDB API.
 */
public class TMDBService {

    private static final String API_KEY = "5fa0e3409a2f8d85bcd647f9816c954b"; // Replace with your TMDB API key
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private final HttpClient httpClient;

    public TMDBService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Fetches popular movies from TMDB API.
     *
     * @return List of popular movies.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public List<Movie> fetchPopularMovies() throws IOException, InterruptedException {
        String url = BASE_URL + "/movie/popular?api_key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return parseMoviesFromResponse(response.body());
    }

    /**
     * Fetches movies by genre from TMDB API.
     *
     * @param genreId TMDB genre ID.
     * @return List of movies in the specified genre.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public List<Movie> fetchMoviesByGenre(int genreId) throws IOException, InterruptedException {
        String url = BASE_URL + "/discover/movie?api_key=" + API_KEY + "&with_genres=" + genreId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return parseMoviesFromResponse(response.body());
    }

    /**
     * Parses the TMDB API response and converts it to a list of Movie objects.
     *
     * @param responseBody The raw JSON response from the TMDB API.
     * @return List of movies.
     */
    private List<Movie> parseMoviesFromResponse(String responseBody) {
        List<Movie> movies = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);
            String title = movieJson.getString("title");
            double rating = movieJson.getDouble("vote_average");
            movies.add(new Movie(title, "Unknown", rating)); // Genre is "Unknown" for simplicity
        }
        return movies;
    }
}