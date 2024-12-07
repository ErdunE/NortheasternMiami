package service;

import org.json.JSONArray;
import org.json.JSONObject;

public class TMDBTrailerFetcher {

    public String fetchTrailerUrl(JSONObject movieDetails) {
        JSONObject videos = movieDetails.optJSONObject("videos");
        if (videos != null) {
            JSONArray results = videos.optJSONArray("results");
            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject video = results.getJSONObject(i);
                    if ("YouTube".equalsIgnoreCase(video.optString("site")) &&
                            "Trailer".equalsIgnoreCase(video.optString("type"))) {
                        return "https://www.youtube.com/watch?v=" + video.optString("key");
                    }
                }
            }
        }
        return "No Trailer Available";
    }
}