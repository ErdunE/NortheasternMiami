package edu.northeastern.numad25sp_erdune;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrimeSearchActivity extends AppCompatActivity {

    private boolean isSearching = false;
    private Thread primeThread = null;
    private int currentNumber = 3;
    private int latestPrime = 2;
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

        if (savedInstanceState != null) {
            isSearching = savedInstanceState.getBoolean("IS_SEARCHING", false);
            currentNumber = savedInstanceState.getInt("CURRENT_NUMBER", 3);
            latestPrime = savedInstanceState.getInt("LATEST_PRIME", 2);
            boolean pacifierState = savedInstanceState.getBoolean("PACIFIER_STATE", false);

            pacifierSwitch.setChecked(pacifierState);
            currentNumberTextView.setText("Current Number: " + currentNumber);
            latestPrimeTextView.setText("Latest Prime: " + latestPrime);

            if (isSearching) {
                primeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        searchForPrimes();
                    }
                });
                primeThread.start();
            }
        }

        findPrimesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSearching) {
                    isSearching = true;
                    currentNumber = 3;
                    latestPrime = 2;

                    primeThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            searchForPrimes();
                        }
                    });

                    primeThread.start();
                }
            }
        });

        terminateSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearching) {
                    isSearching = false;
                }
            }
        });

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
    private void searchForPrimes() {
        while (isSearching) {
            if (isPrime(currentNumber)) {
                latestPrime = currentNumber;
                updateUI("latestPrime");
            }

            updateUI("currentNumber");

            currentNumber += 2;
        }
    }

    private void updateUI(final String flag) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if ("latestPrime".equals(flag)) {
                    latestPrimeTextView.setText("Latest Prime: " + latestPrime);
                } else if ("currentNumber".equals(flag)) {
                    currentNumberTextView.setText("Current Number: " + currentNumber);
                }
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