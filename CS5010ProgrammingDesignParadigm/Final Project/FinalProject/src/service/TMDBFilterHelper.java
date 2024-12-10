package service;

import log.LogHelper;
import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TMDBFilterHelper {

    private static final Logger logger = LogHelper.getLogger(TMDBFilterHelper.class);

    public String buildFilterUrl(String genreIds, String minRating, String maxRating, String language,
                                 String minRuntime, String maxRuntime, String year, String releaseDateLte,
                                 String certification) {
        StringBuilder url = new StringBuilder("/discover/movie?");

        appendQueryParam(url, "with_genres", genreIds);
        appendQueryParam(url, "vote_average.gte", minRating);
        appendQueryParam(url, "vote_average.lte", maxRating);
        appendQueryParam(url, "with_original_language", language);
        appendQueryParam(url, "with_runtime.gte", minRuntime);
        appendQueryParam(url, "with_runtime.lte", maxRuntime);
        appendQueryParam(url, "primary_release_year", year);
        appendQueryParam(url, "primary_release_date.lte", releaseDateLte);

        if (certification != null && !certification.isEmpty()) {
            url.append("certification_country=US&certification=").append(certification).append("&");
        }

        // Remove the trailing '&' if present
        if (url.charAt(url.length() - 1) == '&') {
            url.deleteCharAt(url.length() - 1);
        }

        logger.info("Built filter URL: " + url);
        return url.toString();
    }

    public List<Movie> filterMoviesByRuntime(List<Movie> movies, String minRuntime, String maxRuntime) {
        logger.info("Filtering movies by runtime");
        int min = minRuntime != null ? Integer.parseInt(minRuntime) : Integer.MIN_VALUE;
        int max = maxRuntime != null ? Integer.parseInt(maxRuntime) : Integer.MAX_VALUE;

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            try {
                int runtime = Integer.parseInt(movie.getDuration().replace(" mins", ""));
                if (runtime >= min && runtime <= max) {
                    filteredMovies.add(movie);
                }
            } catch (NumberFormatException e) {
                logger.warning("Invalid runtime format for movie: " + movie.getTitle());
            }
        }
        return filteredMovies;
    }

    public List<Movie> filterMoviesByCertification(List<Movie> movies, String certification) {
        logger.info("Filtering movies by certification");
        if (certification == null || certification.isEmpty()) {
            return movies;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getRatingLevel() != null && !movie.getRatingLevel().equalsIgnoreCase("Unknown")) {
                if (movie.getRatingLevel().equalsIgnoreCase(certification)) {
                    filteredMovies.add(movie);
                }
            }
        }
        return filteredMovies;
    }

    private void appendQueryParam(StringBuilder url, String paramName, String paramValue) {
        if (paramValue != null && !paramValue.isEmpty()) {
            url.append(paramName).append("=").append(paramValue).append("&");
        }
    }
}