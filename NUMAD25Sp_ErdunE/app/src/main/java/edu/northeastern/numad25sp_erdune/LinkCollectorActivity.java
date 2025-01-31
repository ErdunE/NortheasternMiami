package edu.northeastern.numad25sp_erdune;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkCollectorActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "LinkCollectorPrefs";
    private static final String LINKS_KEY = "SavedLinks";

    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private List<String[]> links;
    private String lastAddedLink;

    private Drawable deleteIcon;
    private final ColorDrawable background = new ColorDrawable(Color.RED);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        links = loadLinksFromPreferences();
        adapter = new LinkAdapter(links, this::handleLinkClick, this::showEditLinkDialog);
        recyclerView.setAdapter(adapter);

        deleteIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_delete);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> showAddLinkDialog());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String[] removedLink = links.remove(position);
                adapter.notifyItemRemoved(position);
                saveLinksToPreferences();

                Snackbar.make(recyclerView, "Link deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", v -> {
                            links.add(position, removedLink);
                            adapter.notifyItemInserted(position);
                            saveLinksToPreferences();
                        }).show();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                View itemView = viewHolder.itemView;

                if (dX == 0 && !isCurrentlyActive) {
                    background.setBounds(0, 0, 0, 0);
                    background.draw(c);
                    return;
                }

                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + iconMargin;
                int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();
                int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                int iconRight = itemView.getRight() - iconMargin;

                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                deleteIcon.draw(c);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void handleLinkClick(String url) {
        if (isValidUrl(url)) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "ERROR, Check Your URL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ERROR, Invalid URL", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }


    private void showEditLinkDialog(int position, String name, String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Link");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_link, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.linkNameInput);
        EditText urlInput = dialogView.findViewById(R.id.linkUrlInput);

        nameInput.setText(name);
        urlInput.setText(url);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = nameInput.getText().toString().trim();
            String newUrl = urlInput.getText().toString().trim();

            if (!newName.isEmpty() && !newUrl.isEmpty()) {
                links.set(position, new String[]{newName, newUrl});
                adapter.notifyItemChanged(position);
                saveLinksToPreferences();
                Snackbar.make(recyclerView, "Link updated successfully!", Snackbar.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Both fields are required.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
    private void showAddLinkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Link");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_link, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.linkNameInput);
        EditText urlInput = dialogView.findViewById(R.id.linkUrlInput);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String url = urlInput.getText().toString().trim();

            if (!name.isEmpty() && !url.isEmpty()) {
                links.add(new String[]{name, url});
                adapter.notifyItemInserted(links.size() - 1);
                saveLinksToPreferences();
                Snackbar.make(recyclerView, "Link added successfully!", Snackbar.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Both fields are required.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void saveLinksToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> linkSet = new HashSet<>();
        for (String[] link : links) {
            linkSet.add(link[0] + "::::" + link[1]);
        }

        editor.putStringSet(LINKS_KEY, linkSet);
        editor.apply();
    }

    private List<String[]> loadLinksFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> linkSet = sharedPreferences.getStringSet(LINKS_KEY, new HashSet<>());

        List<String[]> loadedLinks = new ArrayList<>();
        for (String link : linkSet) {
            String[] parts = link.split("::::");
            loadedLinks.add(new String[]{parts[0], parts[1]});
        }
        return loadedLinks;
    }
}
