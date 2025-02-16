package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PetFinderActivity extends AppCompatActivity {
    private static final String TAG = "PetFinderActivity";
    private RecyclerView petRecyclerView;
    private PetAdapter adapter;

    private List<String> types;
    private Spinner typeSpinner;

    private EditText searchBar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        petRecyclerView = findViewById(R.id.petRecyclerView);
        progressBar = findViewById(R.id.progressBar);

        petRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        typeSpinner = findViewById(R.id.typeSpinner);

        progressBar.setVisibility(View.VISIBLE);
        petRecyclerView.setVisibility(View.GONE);


        // Run network request in a background thread
        new Thread(() -> {
            String token = PetfinderAuth.getAccessToken();

            if (token != null){
                //initialize possible pet types from API
                types = new ArrayList<>();
                types = PetfinderAPI.getTypes(token);
                for (String t:types) {
                    Log.d(TAG, t);
                }

                //List<Pet> pets = PetfinderAPI.getPets(token);
                //Since Spinner is initialized with first value, return filtered list right away
                List<Pet> pets = PetfinderAPI.filterPets(token, types.get(0));

                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    petRecyclerView.setVisibility(View.VISIBLE);

                    if (pets != null && !pets.isEmpty()) {
                        // Only set the adapter after fetching the data
                        adapter = new PetAdapter(pets);
                        petRecyclerView.setAdapter(adapter);

                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, types);
                        typeSpinner.setAdapter(spinnerAdapter);
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

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Selected: "+ types.get(i));
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                    petRecyclerView.setVisibility(View.GONE);
                });

                new Thread(() -> {
                    String token = PetfinderAuth.getAccessToken();

                    if (token != null){

                        List<Pet> pets = PetfinderAPI.filterPets(token, types.get(i));

                        runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            petRecyclerView.setVisibility(View.VISIBLE);

                            if (pets != null && !pets.isEmpty()) {
                                // Reset the adapter as the list of pets has been replaced
                                adapter = new PetAdapter(pets);
                                petRecyclerView.setAdapter(adapter);

                            } else {
                                Toast.makeText(PetFinderActivity.this, "No pets of this type found", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
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
                    }
                }).start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //do nothing, but this method must be overridden
            }
        });
    }

}
