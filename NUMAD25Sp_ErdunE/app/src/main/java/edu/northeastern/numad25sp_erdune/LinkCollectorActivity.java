package edu.northeastern.numad25sp_erdune;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private List<String> links;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        links = new ArrayList<>();
        adapter = new LinkAdapter(links);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLinkDialog();
            }
        }));
    }

    private void showAddLinkDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Link");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_link, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.linkNameInput);
        EditText urlInput = dialogView.findViewById(R.id.linkUrlInput);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String url = urlInput.getText().toString().trim();

            if(!name.isEmpty() && !url.isEmpty()){
                links.add(name + ": " + url);
                adapter.notifyDataSetChanged();
                Toast.makeText(LinkCollectorActivity.this, "Link added successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LinkCollectorActivity.this, "Both fields are required.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}
