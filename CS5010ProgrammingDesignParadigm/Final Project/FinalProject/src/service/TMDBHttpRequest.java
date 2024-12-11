package service;

import log.LogHelper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.json.JSONObject;

/**
 * Handles HTTP requests to The Movie Database (TMDB) API.
 * Provides a method to send GET requests and retrieve JSON responses.
 *
 * @author Erdun E
 * @version 1.35
 * @since 2024-12-10
 * Course: CS5010 Program Design Paradigm
 * Program: Final Project
 */
public class TMDBHttpRequest {

    private static final String API_KEY = "297efb0f5cf3a920cebbb9b5f8d2302d";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private final HttpClient httpClient;
    private static final Logger logger = LogHelper.getLogger(TMDBHttpRequest.class);

    /**
     * Initializes a new instance of {@code TMDBHttpRequest}.
     */
    public TMDBHttpRequest() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Sends a GET request to the specified TMDB API endpoint and returns the response as a {@link JSONObject}.
     * Adds the API key automatically to the request.
     *
     * @param endpoint The API endpoint to send the GET request to (e.g., "/movie/popular").
     * @return The response as a {@link JSONObject}, or a JSONObject containing an error message if the request fails.
     * @throws IOException If an I/O error occurs during the request.
     * @throws InterruptedException If the request is interrupted.
     */
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