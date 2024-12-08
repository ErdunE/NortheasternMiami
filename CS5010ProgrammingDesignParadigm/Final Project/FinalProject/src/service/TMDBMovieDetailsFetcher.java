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
            return tmdbHttpRequest.sendGetRequest("/movie/" + movieId + "?append_to_response=videos,keywords,credits");
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

    public String fetchRatingLevel(JSONObject movieJson) {
        JSONObject releaseDates = movieJson.optJSONObject("release_dates");
        if (releaseDates != null) {
            JSONArray results = releaseDates.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject countryRelease = results.getJSONObject(i);
                    JSONArray certifications = countryRelease.optJSONArray("release_dates");
                    if (certifications != null && certifications.length() > 0) {
                        return certifications.getJSONObject(0).optString("certification", "Unknown");
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
}