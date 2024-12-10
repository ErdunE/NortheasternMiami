package service;

import log.LogHelper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.json.JSONObject;

public class TMDBHttpRequest {

    private static final String API_KEY = "297efb0f5cf3a920cebbb9b5f8d2302d";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private final HttpClient httpClient;
    private static final Logger logger = LogHelper.getLogger(TMDBHttpRequest.class);

    public TMDBHttpRequest() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public JSONObject sendGetRequest(String endpoint) throws IOException, InterruptedException {
        String url = BASE_URL + endpoint + (endpoint.contains("?") ? "&" : "?") + "api_key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        LogHelper.logInfo(logger, "Sending GET request to: " + url);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            LogHelper.logInfo(logger, "Received response with status code: " + response.statusCode());
            return new JSONObject(response.body());
        } catch (IOException e) {
            LogHelper.logSevere(logger, "IOException occurred during request: " + e.getMessage(), e);
            return new JSONObject().put("error", "Request failed: " + e.getMessage());
        } catch (InterruptedException e) {
            LogHelper.logWarning(logger, "Request interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
            return new JSONObject().put("error", "Request interrupted: " + e.getMessage());
        }
    }
}