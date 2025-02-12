package edu.northeastern.a6_group_1;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetfinderAPI {
    private static final String API_URL = "https://api.petfinder.com/v2/animals";
    private static final String TAG = "PetfinderAPI";

    public static List<Pet> getPets(String token) {
        List<Pet> petList = new ArrayList<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            Log.d(TAG, "Response Code: " + responseCode);

            if (responseCode != 200) {
                Log.e(TAG, "Error: API request failed with response code " + responseCode);
                return petList;
            }

            // Read entire response
            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");

            // Safeguard in case there are multiple responses
            String response = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Pet Details are named under the 'animals' array
            JSONArray animalsArray = jsonResponse.getJSONArray("animals");

            // Iterating through pets array
            for (int i = 0; i < animalsArray.length(); i++) {
                JSONObject petJson = animalsArray.getJSONObject(i);

                // Extracting pet details
                String name = petJson.optString("name", "Unknown");
                String type = petJson.optString("type", "Unknown");
                String breed = petJson.getJSONObject("breeds").optString("primary", "Unknown");
                String photoUrl = "https://via.placeholder.com/150"; // Default placeholder

                JSONArray photos = petJson.optJSONArray("photos");
                if (photos != null && photos.length() > 0) {
                    photoUrl = photos.getJSONObject(0).optString("medium", photoUrl); // Choosing medium photos for example, we can revisit the size later
                }

                petList.add(new Pet(name, type, breed, photoUrl));
            }

        } catch (Exception e) {
            Log.e(TAG, "Error fetching pet data", e);
        }
        return petList;
    }
}
