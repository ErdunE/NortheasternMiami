package model;

import java.util.List;

/**
 * Represents a movie with a title, genre, and rating.
 */
public class Movie {
    private String title;
    private List<String> genres; // List of genre names
    private double rating;
    private String overview;
    private String posterUrl;

    public Movie(String title, List<String> genres, double rating, String overview, String posterUrl) {
        this.title = title;
        this.genres = genres;
        this.rating = rating;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}