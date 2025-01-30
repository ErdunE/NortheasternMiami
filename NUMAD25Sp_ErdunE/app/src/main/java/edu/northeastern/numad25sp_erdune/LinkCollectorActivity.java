package edu.northeastern.numad25sp_erdune;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import java.util.List;

public class LinkCollectorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinkAdapter adapter;
    private List<String> links;
    private String lastAddedLink;

    private Drawable deleteIcon;
    private final ColorDrawable background = new ColorDrawable(Color.RED);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        links = new ArrayList<>();
        adapter = new LinkAdapter(links, new LinkAdapter.OnLinkLongClickListener() {
            @Override
            public void onLinkLongClick(int position, String link) {
                showEditLinkDialog(position, link);
            }
        });
        recyclerView.setAdapter(adapter);

        deleteIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_delete);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLinkDialog();
            }
        }));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                links.remove(position);
                adapter.notifyItemRemoved(position);
                Snackbar.make(recyclerView, "Link deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                links.add(position, ((LinkAdapter.ViewHolder) viewHolder).linkTextView.getText().toString());
                                adapter.notifyItemInserted(position);
                            }
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

    private void showEditLinkDialog(int position, String link) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Link");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_link, null);
        builder.setView(dialogView);

        EditText nameInput = dialogView.findViewById(R.id.linkNameInput);
        EditText urlInput = dialogView.findViewById(R.id.linkUrlInput);

        String[] parts = link.split(": ", 2);
        nameInput.setText(parts[0]);
        urlInput.setText(parts.length > 1 ? parts[1] : "");

        builder.setPositiveButton("Save", (dialog, which) -> {
            String name = nameInput.getText().toString().trim();
            String url = urlInput.getText().toString().trim();

            if (!name.isEmpty() && !url.isEmpty()) {
                links.set(position, name + ": " + url);
                adapter.notifyItemChanged(position);
                Snackbar.make(recyclerView, "Link updated successfully!", Snackbar.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LinkCollectorActivity.this, "Both fields are required.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
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
                lastAddedLink = name + ": " + url;
                links.add(lastAddedLink);
                adapter.notifyItemInserted(links.size() - 1);

                Snackbar.make(recyclerView, "Link added successfully!", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        links.remove(links.size() - 1);
                        adapter.notifyItemRemoved(links.size());
                    }
                }).show();
            } else {
                Toast.makeText(LinkCollectorActivity.this, "Both fields are required.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}
