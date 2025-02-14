package edu.northeastern.a6_group_1;

import android.util.Log;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PetfinderAuth {
    private static final String CLIENT_ID = "QR0O7Kuejv143PxGVJb4WS18Ey6zuVa8vBzGLvO72PZrwrm7Cn";  // Replaced with actual Key (shared in teams)
    private static final String CLIENT_SECRET = "gAO2IbO9N6eLGt2w6K2Vm4NCRtMcseRbam5VTKo2";  // Replaced with actual Secret (shared in teams)
    private static final String AUTH_URL = "https://api.petfinder.com/v2/oauth2/token";
    private static final String TAG = "PetfinderAuth";

    public static String getAccessToken() {
        try {
            URL url = new URL(AUTH_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Create request body
            String requestBody = "grant_type=client_credentials&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;
            byte[] postData = requestBody.getBytes(StandardCharsets.UTF_8);

            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(postData);
                os.flush();
            }

            // Check HTTP response code
            try {
                int responseCode = conn.getResponseCode();
                Log.d(TAG, "Response Code: " + responseCode);

                if (responseCode != 200) {
                    Log.e(TAG, "Error: API request failed with response code " + responseCode);
                    Scanner errorScanner = new Scanner(conn.getErrorStream()).useDelimiter("\\A");
                    String errorResponse = errorScanner.hasNext() ? errorScanner.next() : "No error response";
                    Log.e(TAG, "Error Response: " + errorResponse);
                    return null;
                }
            } catch (IOException e){
                Log.d(TAG, "IO Exception Thrown");
            }

            // Read the response
            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response);
            String token = jsonResponse.getString("access_token");

            Log.d(TAG, "Access Token: " + token);
            return token;

        } catch (Exception e) {
            Log.e(TAG, "Error getting token", e);
            return null;
        }
    }
}
