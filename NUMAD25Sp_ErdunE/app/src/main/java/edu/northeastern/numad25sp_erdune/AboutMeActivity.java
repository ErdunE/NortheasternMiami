package edu.northeastern.numad25sp_erdune;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        TextView textView = findViewById(R.id.aboutMeText);
        textView.setText("Hello, I’m Erdun! You can reach me at e.e@northeastern.edu");
    }
}
