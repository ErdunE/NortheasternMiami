package service;

import model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TMDBMovieParser {

    private final TMDBMovieDetailsFetcher tmdbMovieDetailsFetcher;
    private final TMDBTrailerFetcher tmdbTrailerFetcher;
    private final TMDBGenreMapper tmdbGenreMapper;

    public TMDBMovieParser() {
        this.tmdbMovieDetailsFetcher = new TMDBMovieDetailsFetcher();
        this.tmdbTrailerFetcher = new TMDBTrailerFetcher();
        this.tmdbGenreMapper = new TMDBGenreMapper();
    }

    public List<Movie> parseMoviesFromResponse(String responseBody) {
        List<Movie> movies = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);

            String title = movieJson.getString("title");
            double rating = movieJson.optDouble("vote_average", 0.0);
            String posterPath = movieJson.optString("poster_path", "");
            String overview = movieJson.optString("overview", "No overview available.");

            String releaseDate = tmdbMovieDetailsFetcher.fetchReleaseDate(movieJson);
            int releaseYear = tmdbMovieDetailsFetcher.fetchReleaseYear(releaseDate);

            int movieId = movieJson.getInt("id");
            JSONObject movieDetails = tmdbMovieDetailsFetcher.fetchMovieDetailsById(movieId);

            long revenue = tmdbMovieDetailsFetcher.fetchRevenue(movieDetails);

            // Genres
            JSONArray genreIds = movieJson.optJSONArray("genre_ids");
            List<String> genres = new ArrayList<>();
            if (genreIds != null) {
                for (int j = 0; j < genreIds.length(); j++) {
                    genres.add(tmdbGenreMapper.getGenreNameById(genreIds.getInt(j)));
                }
            }

            // Duration, ratingLevel, language
            String duration = movieDetails.optInt("runtime", 0) > 0 ? movieDetails.optInt("runtime") + " mins" : "Unknown";
            String ratingLevel = tmdbMovieDetailsFetcher.fetchRatingLevel(movieDetails);
            String language = tmdbMovieDetailsFetcher.fetchLanguages(movieDetails.optJSONArray("spoken_languages"));


            // Keywords
            List<String> keywords = new ArrayList<>();
            JSONObject keywordsObject = movieDetails.optJSONObject("keywords");
            if (keywordsObject != null) {
                JSONArray keywordsArray = keywordsObject.optJSONArray("keywords");
                if (keywordsArray != null) {
                    for (int j = 0; j < Math.min(5, keywordsArray.length()); j++) {
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
                    for (int j = 0; j < Math.min(5, castArray.length()); j++) {
                        cast.add(castArray.getJSONObject(j).optString("name", "Unknown"));
                    }
                }
            }

            // Director
            String director;
            try {
                director = tmdbMovieDetailsFetcher.fetchDirectorByMovieId(movieId);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                director = "Unknown";
            }

            // Trailer URL
            String trailerUrl = tmdbTrailerFetcher.fetchTrailerUrl(movieDetails);
            if (trailerUrl.isEmpty()) {
                trailerUrl = "Trailer not available";
            }

            Movie movie = new Movie(title, posterPath, rating, genres, overview, director, releaseDate,
                    releaseYear, duration, ratingLevel, language, keywords, cast, revenue);
            movie.setTrailerUrl(trailerUrl);
            movies.add(movie);
        }
        return movies;
    }
}