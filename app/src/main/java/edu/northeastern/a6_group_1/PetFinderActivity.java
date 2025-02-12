package edu.northeastern.a6_group_1;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PetFinderActivity extends AppCompatActivity {

    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_finder);

        //Search bar for users to enter what they are looking for
        searchBar = findViewById(R.id.searchBar);
    }
}