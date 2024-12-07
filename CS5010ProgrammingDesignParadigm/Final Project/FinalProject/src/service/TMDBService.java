package service;

import model.Movie;
import java.io.IOException;
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

    private final TMDBHttpRequest tmdbHttpRequest;
    private final TMDBTrailerFetcher tmdbTrailerFetcher;
    private static final Map<String, Integer> GENRE_MAP = new HashMap<>();

    public TMDBService() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
        this.tmdbTrailerFetcher = new TMDBTrailerFetcher();
    }

    /**
     * Fetches popular movies from TMDB API.
     *
     * @return List of popular movies.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    private JSONObject fetchMovieDetails(int movieId) {
        try {
            return tmdbHttpRequest.sendGetRequest("/movie/" + movieId + "?append_to_response=videos,keywords,credits");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public List<Movie> fetchPopularMovies() throws IOException, InterruptedException {
        JSONObject response = tmdbHttpRequest.sendGetRequest("/movie/popular");

        // Log the response for debugging
        System.out.println("API Response: " + response);

        return parseMoviesFromResponse(response.toString());
    }

    private String extractRatingLevel(JSONObject movieJson) {
        JSONObject releaseDates = movieJson.optJSONObject("release_dates");
        if (releaseDates != null) {
            JSONArray results = releaseDates.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject countryRelease = results.getJSONObject(i);
                    JSONArray certifications = countryRelease.optJSONArray("release_dates");
                    if (certifications != null && certifications.length() > 0) {
                        return certifications.getJSONObject(0).optString("certification", "Unknown");
                    }
                }
            }
        }
        return "Unknown";
    }

    private String extractLanguages(JSONArray languagesArray) {
        if (languagesArray == null) return "Unknown";
        List<String> languages = new ArrayList<>();
        for (int i = 0; i < languagesArray.length(); i++) {
            languages.add(languagesArray.getJSONObject(i).optString("english_name", "Unknown"));
        }
        return String.join(", ", languages);
    }

    public List<Movie> fetchMoviesByGenre(int genreId) throws IOException, InterruptedException {
        JSONObject response = tmdbHttpRequest.sendGetRequest("/discover/movie?with_genres=" + genreId);

        return parseMoviesFromResponse(response.toString());
    }

    private String fetchDirector(int movieId) throws IOException, InterruptedException {
        JSONObject response = tmdbHttpRequest.sendGetRequest("/movie/" + movieId + "/credits");
        JSONArray crew = response.optJSONArray("crew");

        for (int i = 0; i < crew.length(); i++) {
            JSONObject person = crew.getJSONObject(i);
            if ("Director".equals(person.optString("job", ""))) {
                return person.optString("name", "Unknown");
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
            double rating = movieJson.optDouble("vote_average", 0.0);
            String posterPath = movieJson.optString("poster_path", "");
            String overview = movieJson.optString("overview", "No overview available.");
            String releaseDate = movieJson.optString("release_date", "");
            int releaseYear = releaseDate.isEmpty() ? 0 : Integer.parseInt(releaseDate.split("-")[0]);

            int movieId = movieJson.getInt("id");
            JSONObject movieDetails = fetchMovieDetails(movieId);

            // Genres
            JSONArray genreIds = movieJson.optJSONArray("genre_ids");
            List<String> genres = new ArrayList<>();
            if (genreIds != null) {
                for (int j = 0; j < genreIds.length(); j++) {
                    genres.add(getGenreNameById(genreIds.getInt(j)));
                }
            }

            // Duration, ratingLevel, language
            String duration = movieDetails.optInt("runtime", 0) > 0 ? movieDetails.optInt("runtime") + " mins" : "Unknown";
            String ratingLevel = extractRatingLevel(movieDetails);
            String language = extractLanguages(movieDetails.optJSONArray("spoken_languages"));

            // Keywords
            List<String> keywords = new ArrayList<>();
            JSONObject keywordsObject = movieDetails.optJSONObject("keywords");
            if (keywordsObject != null) {
                JSONArray keywordsArray = keywordsObject.optJSONArray("keywords");
                if (keywordsArray != null) {
                    for (int j = 0; j < keywordsArray.length(); j++) {
                        keywords.add(keywordsArray.getJSONObject(j).optString("name", "Unknown"));
                    }
                }
            }

            // Cast
            List<String> cast = new ArrayList<>();
            JSONObject credits = movieDetails.optJSONObject("credits");
            if (credits != null) {
                JSONArray castArray = credits.optJSONArray("cast");
                if (castArray != null) {
                    for (int j = 0; j < Math.min(5, castArray.length()); j++) { // Top 5 actors
                        cast.add(castArray.getJSONObject(j).optString("name", "Unknown"));
                    }
                }
            }

            // Director
            String director;
            try {
                director = fetchDirector(movieId);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                director = "Unknown";
            }

            String trailerUrl = tmdbTrailerFetcher.fetchTrailerUrl(movieDetails);
            if (trailerUrl.isEmpty()) {
                trailerUrl = "Trailer not available";
            }

            Movie movie = new Movie(title, posterPath, rating, genres, overview, director, releaseYear,
                    duration, ratingLevel, language, keywords, cast);
            movie.setTrailerUrl(trailerUrl);
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

