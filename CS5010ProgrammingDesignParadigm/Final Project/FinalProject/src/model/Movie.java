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
    private String director;
    private int releaseYear;

    // New fields for additional details
    private String duration;
    private String ratingLevel;
    private String language;
    private List<String> keywords;
    private List<String> cast;

    public Movie(String title, String posterPath, double rating, List<String> genres, String overview,
                 String director, int releaseYear, String duration, String ratingLevel,
                 String language, List<String> keywords, List<String> cast) {
        this.title = title;
        this.posterPath = posterPath != null ? posterPath : "";
        this.rating = rating;
        this.genres = genres;
        this.overview = overview;
        this.director = director;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.ratingLevel = ratingLevel;
        this.language = language;
        this.keywords = keywords;
        this.cast = cast;
    }

    // Getters for existing fields
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

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    // New getters for additional details
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRatingLevel() {
        return ratingLevel;
    }

    public void setRatingLevel(String ratingLevel) {
        this.ratingLevel = ratingLevel;
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

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    private String trailerUrl;

    // Getter and Setter for trailerUrl
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}