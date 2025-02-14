package edu.northeastern.a6_group_1;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetfinderAPI {
    private static final String API_URL = "https://api.petfinder.com/v2/animals";
    private static final String TAG = "PetfinderAPI";

    /**
     * Default API call that returns a page of animals.
     * @param token auth token
     * @return list of pets
     */
    public static List<Pet> getPets(String token) {
        List<Pet> petList = new ArrayList<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            try {
                int responseCode = conn.getResponseCode();
                Log.d(TAG, "Response Code: " + responseCode);

                if (responseCode != 200) {
                    Log.e(TAG, "Error: API request failed with response code " + responseCode);
                    return petList;
                }
            } catch (IOException e){
                Log.e(TAG, "IO Exception thrown");
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
                String photoUrl = "https://placedog.net/300/300"; // Default placeholder

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

    /**
     * Takes a type filter to pass to the API, returns a filtered list of pets.
     * @param token auth token
     * @param selectedType pet type to filter on
     * @return list of Pet objects to display to user
     */
    public static List<Pet> filterPets(String token, String selectedType) {
        List<Pet> petList = new ArrayList<>();
        //handle spaces and ampersands in types
        String urlFriendlyType = selectedType.replace("&","%26");
        urlFriendlyType = urlFriendlyType.replace(" ", "%20");
        String filterUrl = API_URL + "?type="+ urlFriendlyType;
        try {
            URL queryUrl = new URL(filterUrl);
            HttpURLConnection conn = (HttpURLConnection) queryUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            try {
                int responseCode = conn.getResponseCode();
                Log.d(TAG, "Response Code: " + responseCode);

                if (responseCode != 200) {
                    Log.e(TAG, "Error: API request failed with response code " + responseCode);
                    Log.e(TAG, conn.getResponseMessage());
                    return petList;
                }
            } catch (IOException e){
                Log.e(TAG, "IO Exception thrown");
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
                String photoUrl = "https://placedog.net/300/300"; // Default placeholder

                JSONArray photos = petJson.optJSONArray("photos");
                if (photos != null && photos.length() > 0) {
                    photoUrl = photos.getJSONObject(0).optString("medium", photoUrl); // Choosing medium photos for example, we can revisit the size later
                }

                petList.add(new Pet(name, type, breed, photoUrl));
            }

        } catch (Exception e) {
            Log.e(TAG, "Error fetching filtered pet data", e);
        }
        return petList;
    }

    /**
     * Method to call getAnimalTypes API call.
     * @param token auth token
     * @return list of animal types returned from API
     */
    public static List<String> getTypes(String token) {
        String GET_TYPE_URL = "https://api.petfinder.com/v2/types";
        List<String> typeList = new ArrayList<>();
        try {
            URL url = new URL(GET_TYPE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            try {
                int responseCode = conn.getResponseCode();
                Log.d(TAG, "Response Code: " + responseCode);

                if (responseCode != 200) {
                    Log.e(TAG, "Error: API request failed with response code " + responseCode);
                    return typeList;
                }
            } catch (IOException e){
                Log.e(TAG, "IO Exception thrown");
            }

            // Read entire response
            Scanner scanner = new Scanner(conn.getInputStream()).useDelimiter("\\A");

            // Safeguard in case there are multiple responses
            String response = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Possible types are named under the 'types' array
            JSONArray typesArray = jsonResponse.getJSONArray("types");

            // Iterating through types array
            for (int i = 0; i < typesArray.length(); i++) {
                JSONObject typeJson = typesArray.getJSONObject(i);

                // Extracting pet details
                String type = typeJson.optString("name", "Unknown");

                typeList.add(type);
            }

        } catch (Exception e) {
            Log.e(TAG, "Error fetching pet types", e);
        }

        return typeList;
    }
}
