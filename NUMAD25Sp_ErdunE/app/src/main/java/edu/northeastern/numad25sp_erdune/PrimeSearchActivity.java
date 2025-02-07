package edu.northeastern.numad25sp_erdune;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PrimeSearchActivity extends AppCompatActivity {

    private boolean isSearching = false;
    private int currentNumber = 3;
    private int latestPrime = 2;
    private Handler handler;
    private Button findPrimesButton;
    private Button terminateSearchButton;
    private CheckBox pacifierSwitch;
    private TextView currentNumberTextView;
    private TextView latestPrimeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_search);

        findPrimesButton = findViewById(R.id.findPrimesButton);
        terminateSearchButton = findViewById(R.id.terminateSearchButton);
        pacifierSwitch = findViewById(R.id.pacifierSwitch);
        currentNumberTextView = findViewById(R.id.currentNumberTextView);
        latestPrimeTextView = findViewById(R.id.latestPrimeTextView);

        handler = new Handler(Looper.getMainLooper());

        if (savedInstanceState != null) {
            isSearching = savedInstanceState.getBoolean("IS_SEARCHING", false);
            currentNumber = savedInstanceState.getInt("CURRENT_NUMBER", 3);
            latestPrime = savedInstanceState.getInt("LATEST_PRIME", 2);
            boolean pacifierState = savedInstanceState.getBoolean("PACIFIER_STATE", false);

            pacifierSwitch.setChecked(pacifierState);
            updateUI("currentNumber");
            updateUI("latestPrime");

            if (isSearching) {
                startSearchInChunks();
            }
        }

        findPrimesButton.setOnClickListener(v -> {
            if (!isSearching) {
                isSearching = true;
                currentNumber = 3;
                latestPrime = 2;
                startSearchInChunks();
            }
        });

        terminateSearchButton.setOnClickListener(v -> isSearching = false);

        pacifierSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("IS_SEARCHING", isSearching);
        outState.putInt("CURRENT_NUMBER", currentNumber);
        outState.putInt("LATEST_PRIME", latestPrime);
        outState.putBoolean("PACIFIER_STATE", pacifierSwitch.isChecked());
    }

    @Override
    public void onBackPressed() {
        if (isSearching) {
            new AlertDialog.Builder(this)
                    .setTitle("Search in progress")
                    .setMessage("Are you sure you want to end the search and exit?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        isSearching = false;
                        finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                    })
                    .show();
        } else {
            super.onBackPressed();
        }
    }
    private void startSearchInChunks() {
        if (!isSearching) return;

        int chunkSize = 5_432;
        while (isSearching && chunkSize > 0) {
            if (isPrime(currentNumber)) {
                latestPrime = currentNumber;
                updateUI("latestPrime");
            }
            currentNumber += 2;
            chunkSize--;

            if (chunkSize % 100 == 0) {
                updateUI("currentNumber");
            }
        }

        updateUI("currentNumber");

        if (isSearching) {
            handler.postDelayed(this::startSearchInChunks, 50);
        }
    }


    private void updateUI(final String flag) {
        handler.post(() -> {
            if ("latestPrime".equals(flag)) {
                latestPrimeTextView.setText("Latest Prime: " + latestPrime);
            } else if ("currentNumber".equals(flag)) {
                String formattedNumber = String.valueOf(currentNumber);
                currentNumberTextView.setText("Current Number: " + formattedNumber);
            }
        });
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}