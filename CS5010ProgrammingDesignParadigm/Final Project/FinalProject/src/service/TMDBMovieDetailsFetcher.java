package service;

import log.LogHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TMDBMovieDetailsFetcher {

    private static final Logger logger = LogHelper.getLogger(TMDBMovieDetailsFetcher.class);
    private final TMDBHttpRequest tmdbHttpRequest;

    public TMDBMovieDetailsFetcher() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
    }

    public JSONObject fetchMovieDetailsById(int movieId) {
        String endpoint = "/movie/" + movieId + "?append_to_response=videos,keywords,credits,release_dates";
        logger.info("Fetching movie details for movie ID: " + movieId);

        try {
            return tmdbHttpRequest.sendGetRequest(endpoint);
        } catch (IOException | InterruptedException e) {
            logger.severe("Failed to fetch movie details for movie ID " + movieId + ": " + e.getMessage());
            return new JSONObject();
        }
    }

    public String fetchDirectorByMovieId(int movieId) {
        String endpoint = "/movie/" + movieId + "/credits";
        logger.info("Fetching director for movie ID: " + movieId);

        try {
            JSONObject response = tmdbHttpRequest.sendGetRequest(endpoint);
            JSONArray crew = response.optJSONArray("crew");

            for (int i = 0; crew != null && i < crew.length(); i++) {
                JSONObject person = crew.getJSONObject(i);
                if ("Director".equals(person.optString("job", ""))) {
                    String directorName = person.optString("name", "Unknown");
                    logger.info("Director found: " + directorName);
                    return directorName;
                }
            }
        } catch (Exception e) {
            logger.severe("Failed to fetch director for movie ID " + movieId + ": " + e.getMessage());
        }

        logger.warning("Director not found, returning 'Unknown'.");
        return "Unknown";
    }

    public String fetchRatingLevel(JSONObject movieDetails) {
        logger.info("Fetching rating level from movie details.");
        JSONObject releaseDates = movieDetails.optJSONObject("release_dates");

        if (releaseDates != null) {
            JSONArray results = releaseDates.optJSONArray("results");
            for (int i = 0; results != null && i < results.length(); i++) {
                JSONObject countryRelease = results.getJSONObject(i);
                if ("US".equalsIgnoreCase(countryRelease.optString("iso_3166_1"))) {
                    JSONArray certifications = countryRelease.optJSONArray("release_dates");
                    if (certifications != null && !certifications.isEmpty()) {
                        String certification = certifications.getJSONObject(0).optString("certification", "Unknown");
                        if (!certification.isEmpty()) {
                            logger.info("Rating level found: " + certification);
                            return certification;
                        }
                    }
                }
            }
        }

        logger.info("Rating level not found, returning 'Unknown'.");
        return "Unknown";
    }

    public String fetchLanguages(JSONArray languagesArray) {
        if (languagesArray == null) {
            logger.warning("Languages array is null.");
            return "Unknown";
        }

        List<String> languages = new ArrayList<>();
        for (int i = 0; i < languagesArray.length(); i++) {
            String language = languagesArray.getJSONObject(i).optString("english_name", "Unknown");
            languages.add(language);
        }

        String result = String.join(", ", languages);
        logger.info("Fetched languages: " + result);
        return result;
    }

    public String fetchReleaseDate(JSONObject movieJson) {
        String releaseDate = movieJson.optString("release_date", "Unknown");
        logger.info("Fetched release date: " + releaseDate);
        return releaseDate;
    }

    public int fetchReleaseYear(String releaseDate) {
        if (releaseDate != null && !releaseDate.isEmpty() && releaseDate.contains("-")) {
            try {
                int year = Integer.parseInt(releaseDate.split("-")[0]);
                logger.info("Fetched release year: " + year);
                return year;
            } catch (NumberFormatException e) {
                logger.severe("Failed to parse release year from date: " + releaseDate);
            }
        }

        logger.warning("Release date is invalid or empty, returning default year 0.");
        return 0; // Default year if extraction fails
    }

    public long fetchRevenue(JSONObject movieDetails) {
        long revenue = movieDetails.optLong("revenue", 0);
        logger.info("Fetched revenue: " + revenue);
        return revenue > 0 ? revenue : 0;
    }

    public String fetchTrailerUrl(JSONObject movieDetails) {
        logger.info("Fetching trailer URL from movie details.");
        JSONObject videos = movieDetails.optJSONObject("videos");
        if (videos != null) {
            JSONArray results = videos.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject video = results.getJSONObject(i);
                    if ("YouTube".equalsIgnoreCase(video.optString("site")) &&
                            "Trailer".equalsIgnoreCase(video.optString("type"))) {
                        String trailerUrl = "https://www.youtube.com/watch?v=" + video.optString("key");
                        logger.info("Trailer URL found: " + trailerUrl);
                        return trailerUrl;
                    }
                }
            }
        }
        logger.info("No trailer available.");
        return "No Trailer Available";
    }
}