package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PetFinderActivity extends AppCompatActivity {
    private static final String TAG = "PetFinderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        // Run network request in a background thread
        new Thread(() -> {
            String token = PetfinderAuth.getAccessToken();

            if (token != null){
                List<Pet> pets = PetfinderAPI.getPets(token);

                for (Pet pet : pets){
                    Log.d(TAG, "Pet Name: " + pet.getName());
                    Log.d(TAG, "Pet Type: " + pet.getType());
                    Log.d(TAG, "Pet Breed: " + pet.getBreed());
                    Log.d(TAG, "Pet Image URL: " + pet.getImageUrl());
                    Log.d(TAG, "-----------------------------------");
                }
            } else {
                Log.e(TAG, "Failed to receive access token");
            }
        }).start();
    }
}
