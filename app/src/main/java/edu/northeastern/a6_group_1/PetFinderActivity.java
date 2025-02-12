package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class PetFinderActivity extends AppCompatActivity {
    private static final String TAG = "PetFinderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        // Run network request in a background thread
        new Thread(() -> {
            String token = PetfinderAuth.getAccessToken();
            if (token != null) {
                Log.d(TAG, "Received Access Token: " + token);
                // TODO: Use this token for fetching pet data
            } else {
                Log.e(TAG, "Failed to retrieve access token");
            }
        }).start();
    }
}
