package service;

import log.LogHelper;
import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Helper class to build filter URLs and filter movies based on various criteria.
 * Provides methods to construct URLs with query parameters and filter movie lists.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBFilterHelper {

    private static final Logger logger = LogHelper.getLogger(TMDBFilterHelper.class);

    /**
     * Builds a URL for filtering movies based on various criteria.
     *
     * @param genreIds       Genre IDs for filtering.
     * @param minRating      Minimum rating for filtering.
     * @param maxRating      Maximum rating for filtering.
     * @param language       Language code for filtering.
     * @param minRuntime     Minimum runtime for filtering.
     * @param maxRuntime     Maximum runtime for filtering.
     * @param year           Release year for filtering.
     * @param releaseDateLte Latest release date for filtering.
     * @param certification  Certification level for filtering.
     * @return A complete URL string with the specified query parameters.
     */
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

        if (url.charAt(url.length() - 1) == '&') {
            url.deleteCharAt(url.length() - 1);
        }

        logger.info("Built filter URL: " + url);
        return url.toString();
    }

    /**
     * Filters a list of movies by their runtime.
     *
     * @param movies     The list of movies to be filtered.
     * @param minRuntime The minimum runtime in minutes.
     * @param maxRuntime The maximum runtime in minutes.
     * @return A list of movies that fall within the specified runtime range.
     */
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

    /**
     * Filters a list of movies by their certification.
     *
     * @param movies       The list of movies to be filtered.
     * @param certification The certification level to filter by.
     * @return A list of movies that match the specified certification.
     */
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

    /**
     * Appends a query parameter to the URL if the value is not null or empty.
     *
     * @param url        The StringBuilder object for the URL.
     * @param paramName  The name of the query parameter.
     * @param paramValue The value of the query parameter.
     */
    private void appendQueryParam(StringBuilder url, String paramName, String paramValue) {
        if (paramValue != null && !paramValue.isEmpty()) {
            url.append(paramName).append("=").append(paramValue).append("&");
        }
    }
}