package edu.northeastern.a6_group_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetHolder> {

    private List<Pet> petList;

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pet_card, parent, false);
        return new PetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = petList.get(position);

        holder.pet_name.setText(pet.getName());
        holder.pet_breed.setText(pet.getBreed());
        holder.pet_type.setText(pet.getType());
        loadImage(holder.photo_url, pet.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    // Method to load image in background and update the UI on the main thread
    private void loadImage(final ImageView imageView, final String imageUrl) {
        // Create a new thread to download the image
        new Thread(() -> {
            try {
                // Download the image from the URL
                InputStream inputStream = new URL(imageUrl).openStream();
                // Decode the InputStream into a Bitmap
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Use a Handler to update the UI on the main thread
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
