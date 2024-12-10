package service;

import log.LogHelper;
import model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TMDBMovieParser {

    private static final Logger logger = LogHelper.getLogger(TMDBMovieParser.class);

    private final TMDBMovieDetailsFetcher tmdbMovieDetailsFetcher;
    private final TMDBGenreMapper tmdbGenreMapper;

    public TMDBMovieParser() {
        this.tmdbMovieDetailsFetcher = new TMDBMovieDetailsFetcher();
        this.tmdbGenreMapper = new TMDBGenreMapper();
    }

    public List<Movie> parseMoviesFromResponse(String responseBody) {
        logger.info("Starting to parse movies from response.");

        List<Movie> movies = new ArrayList<>();
        JSONObject json = new JSONObject(responseBody);
        JSONArray results = json.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movieJson = results.getJSONObject(i);
            logger.info("Parsing movie at index: " + i);

            String title = movieJson.getString("title");
            double rating = movieJson.optDouble("vote_average", 0.0);
            String posterPath = movieJson.optString("poster_path", "");
            String overview = movieJson.optString("overview", "No overview available.");

            String releaseDate = tmdbMovieDetailsFetcher.fetchReleaseDate(movieJson);
            int releaseYear = tmdbMovieDetailsFetcher.fetchReleaseYear(releaseDate);

            int movieId = movieJson.getInt("id");
            logger.info("Fetching details for movie ID: " + movieId);
            JSONObject movieDetails = tmdbMovieDetailsFetcher.fetchMovieDetailsById(movieId);

            long revenue = tmdbMovieDetailsFetcher.fetchRevenue(movieDetails);
            List<String> genres = parseGenres(movieJson);
            String duration = parseDuration(movieDetails);
            String ratingLevel = parseRatingLevel(movieDetails);
            String language = tmdbMovieDetailsFetcher.fetchLanguages(movieDetails.optJSONArray("spoken_languages"));
            List<String> keywords = parseKeywords(movieDetails);
            List<String> cast = parseCast(movieDetails);
            String director = fetchDirector(movieId);
            String trailerUrl = fetchTrailerUrl(movieDetails);

            Movie movie = new Movie(title, posterPath, rating, genres, overview, director, releaseDate,
                    releaseYear, duration, ratingLevel, language, keywords, cast, revenue);
            movie.setTrailerUrl(trailerUrl);
            movies.add(movie);

            logger.info("Successfully parsed movie: " + title);
        }

        logger.info("Completed parsing all movies.");
        return movies;
    }

    private List<String> parseGenres(JSONObject movieJson) {
        List<String> genres = new ArrayList<>();
        JSONArray genreIds = movieJson.optJSONArray("genre_ids");
        if (genreIds != null) {
            for (int i = 0; i < genreIds.length(); i++) {
                genres.add(tmdbGenreMapper.getNameByGenreId(genreIds.getInt(i)));
            }
        }
        logger.info("Parsed genres: " + genres);
        return genres;
    }

    private String parseDuration(JSONObject movieDetails) {
        int runtime = movieDetails.optInt("runtime", 0);
        String duration = runtime > 0 ? runtime + " mins" : "Unknown";
        logger.info("Parsed duration: " + duration);
        return duration;
    }

    private String parseRatingLevel(JSONObject movieDetails) {
        String ratingLevel = tmdbMovieDetailsFetcher.fetchRatingLevel(movieDetails);
        if (ratingLevel == null || ratingLevel.isEmpty()) {
            ratingLevel = "Unrated";
        }
        logger.info("Parsed rating level: " + ratingLevel);
        return ratingLevel;
    }

    private List<String> parseKeywords(JSONObject movieDetails) {
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
        logger.info("Parsed keywords: " + keywords);
        return keywords;
    }

    private List<String> parseCast(JSONObject movieDetails) {
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
        logger.info("Parsed cast: " + cast);
        return cast;
    }

    private String fetchDirector(int movieId) {
        return tmdbMovieDetailsFetcher.fetchDirectorByMovieId(movieId);
    }

    private String fetchTrailerUrl(JSONObject movieDetails) {
        return tmdbMovieDetailsFetcher.fetchTrailerUrl(movieDetails);
    }
}