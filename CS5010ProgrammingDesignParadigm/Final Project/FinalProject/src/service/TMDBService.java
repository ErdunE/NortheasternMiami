package service;

import model.Movie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Service class for interacting with the TMDB API.
 */
public class TMDBService {

    private static final String API_KEY = "5fa0e3409a2f8d85bcd647f9816c954b";
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private final HttpClient httpClient;
    private static final Map<String, Integer> GENRE_MAP = new HashMap<>();

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

    public List<String> fetchMovieTrailers(List<Integer> movieIds) throws IOException, InterruptedException {
        List<String> trailerUrls = new ArrayList<>();
        for (int movieId : movieIds) {
            String url = BASE_URL + "/movie/" + movieId + "/videos?api_key=" + API_KEY;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            trailerUrls.addAll(parseTrailerUrls(response.body()));
        }
        return trailerUrls;
    }

    private List<String> parseTrailerUrls(String responseBody) {
        List<String> trailerUrls = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject video = results.getJSONObject(i);
            if ("YouTube".equalsIgnoreCase(video.getString("site")) && "Trailer".equalsIgnoreCase(video.getString("type"))) {
                String key = video.getString("key");
                trailerUrls.add("https://www.youtube.com/watch?v=" + key);
            }
        }
        return trailerUrls;
    }

    private String fetchDirector(int movieId) throws IOException, InterruptedException {
        String url = BASE_URL + "/movie/" + movieId + "/credits?api_key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        JSONArray crew = json.getJSONArray("crew");

        for (int i = 0; i < crew.length(); i++) {
            JSONObject person = crew.getJSONObject(i);
            if ("Director".equals(person.getString("job"))) {
                return person.getString("name");
            }
        }
        return "Unknown";
    }

    private List<Movie> parseMoviesFromResponse(String responseBody) {
        List<Movie> movies = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);
            String title = movieJson.getString("title");
            double rating = movieJson.getDouble("vote_average");
            String posterPath = movieJson.optString("poster_path", "");
            String overview = movieJson.optString("overview", "");
            String releaseDate = movieJson.optString("release_date", "");

            int releaseYear = releaseDate.isEmpty() ? 0 : Integer.parseInt(releaseDate.split("-")[0]);

            String director;
            try {
                director = fetchDirector(movieJson.getInt("id"));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                director = "Unknown";
            }

            JSONArray genreIds = movieJson.optJSONArray("genre_ids");
            List<String> genres = new ArrayList<>();
            if (genreIds != null) {
                for (int j = 0; j < genreIds.length(); j++) {
                    genres.add(getGenreNameById(genreIds.getInt(j)));
                }
            }

            Movie movie = new Movie(title, posterPath, rating, genres, overview, director, releaseYear);
            movies.add(movie);
        }
        return movies;
    }

    static {
        GENRE_MAP.put("Action", 28);
        GENRE_MAP.put("Adventure", 12);
        GENRE_MAP.put("Animation", 16);
        GENRE_MAP.put("Comedy", 35);
        GENRE_MAP.put("Crime", 80);
        GENRE_MAP.put("Documentary", 99);
        GENRE_MAP.put("Drama", 18);
        GENRE_MAP.put("Family", 10751);
        GENRE_MAP.put("Fantasy", 14);
        GENRE_MAP.put("History", 36);
        GENRE_MAP.put("Horror", 27);
        GENRE_MAP.put("Music", 10402);
        GENRE_MAP.put("Mystery", 9648);
        GENRE_MAP.put("Romance", 10749);
        GENRE_MAP.put("Science Fiction", 878);
        GENRE_MAP.put("TV Movie", 10770);
        GENRE_MAP.put("Thriller", 53);
        GENRE_MAP.put("War", 10752);
        GENRE_MAP.put("Western", 37);
    }

    public int getGenreIdByName(String genreName) {
        return GENRE_MAP.getOrDefault(genreName, -1);
    }

    private String getGenreNameById(int id) {
        return GENRE_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(id))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Unknown");
    }
}