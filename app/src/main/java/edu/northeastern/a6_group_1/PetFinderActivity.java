package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
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
    private EditText searchBar;
    private List<String> types;
    private Spinner typeSpinner;
    private final Handler textHandler;

    public PetFinderActivity() {
        this.textHandler = new Handler();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        searchBar = findViewById(R.id.searchBar);
        petRecyclerView = findViewById(R.id.petRecyclerView);
        petRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        typeSpinner = findViewById(R.id.typeSpinner);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "Selected: "+ types.get(i));

                new Thread(() -> {
                String token = PetfinderAuth.getAccessToken();

                if (token != null){
                    List<Pet> pets = PetfinderAPI.filterPets(token, types.get(i));

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
                //change nothing
            }
        });

        // Run network request in a background thread
        new Thread(() -> {
            String token = PetfinderAuth.getAccessToken();

            if (token != null){
                //initialize possible types from API
                types = new ArrayList<>();
                types = PetfinderAPI.getTypes(token);
                /*for (String t:types) {
                    Log.d(TAG, t);
                }*/

                //let main thread update the Spinner view
                setSpinnerAdapter(textHandler);
                List<Pet> pets = PetfinderAPI.getPets(token);

                runOnUiThread(() -> {
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
            }
        }).start();

    }

    /**
     * Method to set Spinner adapter once list of types has been initialized from API.
     * @param textHandler Handler for updating UI
     */
    private void setSpinnerAdapter(Handler textHandler) {
        textHandler.post(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, types);
                typeSpinner.setAdapter(adapter);
            }
        });

    }
}
