package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TMDBMovieDetailsFetcher {

    private final TMDBHttpRequest tmdbHttpRequest;

    public TMDBMovieDetailsFetcher() {
        this.tmdbHttpRequest = new TMDBHttpRequest();
    }

    public JSONObject fetchMovieDetailsById(int movieId) {
        try {
            return tmdbHttpRequest.sendGetRequest("/movie/" + movieId + "?append_to_response=videos,keywords,credits,release_dates");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String fetchDirectorByMovieId(int movieId) throws IOException, InterruptedException {
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

    public String fetchRatingLevel(JSONObject movieDetails) {
        JSONObject releaseDates = movieDetails.optJSONObject("release_dates");
        if (releaseDates != null) {
            JSONArray results = releaseDates.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject countryRelease = results.getJSONObject(i);
                    if ("US".equalsIgnoreCase(countryRelease.optString("iso_3166_1"))) {
                        JSONArray certifications = countryRelease.optJSONArray("release_dates");
                        if (certifications != null && certifications.length() > 0) {
                            String certification = certifications.getJSONObject(0).optString("certification", "Unknown");
                            if (!certification.isEmpty()) {
                                return certification;
                            }
                        }
                    }
                }
            }
        }
        return "Unknown";
    }

    public String fetchLanguages(JSONArray languagesArray) {
        if (languagesArray == null) return "Unknown";
        List<String> languages = new ArrayList<>();
        for (int i = 0; i < languagesArray.length(); i++) {
            languages.add(languagesArray.getJSONObject(i).optString("english_name", "Unknown"));
        }
        return String.join(", ", languages);
    }

    public String fetchReleaseDate(JSONObject movieJson) {
        return movieJson.optString("release_date", "Unknown");
    }

    public int fetchReleaseYear(String releaseDate) {
        if (releaseDate != null && !releaseDate.isEmpty() && releaseDate.contains("-")) {
            try {
                return Integer.parseInt(releaseDate.split("-")[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0; // Default year if extraction fails
    }

    public long fetchRevenue(JSONObject movieDetails) {
        long revenue = movieDetails.optLong("revenue", 0);
        return revenue > 0 ? revenue : 0;
    }


}