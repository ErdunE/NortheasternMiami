package service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class TMDBHttpRequest {

    private static final String API_KEY = "297efb0f5cf3a920cebbb9b5f8d2302d";
    private static final String BASE_URL = "https://api.themoviedb.org/3";
    private final HttpClient httpClient;

    public TMDBHttpRequest() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Sends a GET request to the specified endpoint and returns the response as a JSONObject.
     *
     * @param endpoint The API endpoint (excluding the base URL).
     * @return JSONObject containing the API response.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public JSONObject sendGetRequest(String endpoint) throws IOException, InterruptedException {
        String url = BASE_URL + endpoint + (endpoint.contains("?") ? "&" : "?") + "api_key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}