package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PetFinderActivity extends AppCompatActivity {
    private static final String TAG = "PetFinderActivity";
    private RecyclerView petRecyclerView;
    private PetAdapter adapter;
    private EditText searchBar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        searchBar = findViewById(R.id.searchBar);
        petRecyclerView = findViewById(R.id.petRecyclerView);
        progressBar = findViewById(R.id.progressBar);

        petRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        petRecyclerView.setVisibility(View.GONE);

        // Run network request in a background thread
        new Thread(() -> {
            String token = PetfinderAuth.getAccessToken();

            if (token != null){
                List<Pet> pets = PetfinderAPI.getPets(token);

                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    petRecyclerView.setVisibility(View.VISIBLE);

                    if (pets != null && !pets.isEmpty()) {
                        // Only set the adapter after fetching the data
                        adapter = new PetAdapter(pets);
                        petRecyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(PetFinderActivity.this, "No pets found", Toast.LENGTH_SHORT).show();
                    }
                });

                for (Pet pet : pets){
                    Log.d(TAG, "Pet Name: " + pet.getName());
                    Log.d(TAG, "Pet Type: " + pet.getType());
                    Log.d(TAG, "Pet Breed: " + pet.getBreed());
                    Log.d(TAG, "Pet Image URL: " + pet.getImageUrl());
                    Log.d(TAG, "-----------------------------------");
                }
            } else {
                Log.e(TAG, "Failed to receive access token");
                runOnUiThread(() -> progressBar.setVisibility(View.GONE));
            }
        }).start();
    }
}
