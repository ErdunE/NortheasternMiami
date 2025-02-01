package edu.northeastern.numad25sp_erdune;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // About Me Button
        Button aboutMeButton = findViewById(R.id.aboutMeButton);
        aboutMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });

        // Quic Calc Button
        Button quicCalcButton = findViewById(R.id.quicCalcButton);
        quicCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start QuicCalcActivity
                Intent intent = new Intent(MainActivity.this, QuicCalcActivity.class);
                startActivity(intent);
            }
        });

        // Link Collector Button
        Button linkCollectorButton = findViewById(R.id.linkCollectorButton);
        linkCollectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start LinkCollectorActivity
                Intent intent = new Intent(MainActivity.this, LinkCollectorActivity.class);
                startActivity(intent);
            }
        });
    }
}