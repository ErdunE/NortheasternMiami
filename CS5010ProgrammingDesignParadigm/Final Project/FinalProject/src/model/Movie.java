package model;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public Movie(String title, String posterPath, double rating, List<String> genres, String overview, String director, int releaseYear) {
        this.title = title;
        this.genres = genres;
        this.rating = rating;
        this.overview = overview;
        this.posterPath = posterPath != null ? posterPath : "";
        this.director = director;
        this.releaseYear = releaseYear;
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
        return "https://image.tmdb.org/t/p/w200" + posterPath; // 确保 URL 拼接正确
    }

    // Getter and Setter for Director
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    // Getter and Setter for Release Year
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}