package model;

import java.util.List;

/**
 * Represents a movie with a title, genre, and rating.
 */
public class Movie {
    private final String title;
    private final List<String> genres;
    private final double rating;
    private final String overview;
    private final String posterPath;
    private final String director;
    private final String releaseDate;
    private final int releaseYear;
    private final String duration;
    private final String ratingLevel;
    private final List<String> keywords;
    private final List<String> cast;
    private final long revenue;
    private String language;
    private String trailerUrl;

    public Movie(String title, String posterPath, double rating, List<String> genres, String overview,
                 String director, String releaseDate, int releaseYear, String duration, String ratingLevel,
                 String language, List<String> keywords, List<String> cast, long revenue) {
        this.title = title;
        this.posterPath = posterPath != null ? posterPath : "";
        this.rating = rating;
        this.genres = genres;
        this.overview = overview;
        this.director = director;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.ratingLevel = ratingLevel;
        this.language = language;
        this.keywords = keywords;
        this.cast = cast;
        this.revenue = revenue;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w200" + posterPath;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDuration() {
        return duration;
    }

    public String getRatingLevel() {
        return ratingLevel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getRevenue() {
        return revenue > 0 ? String.format("$%,d", revenue) : "NO UPDATE";
    }
}