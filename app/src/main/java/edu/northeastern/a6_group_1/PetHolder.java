package edu.northeastern.a6_group_1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetHolder extends RecyclerView.ViewHolder {

    public TextView pet_name;
    public TextView pet_breed;
    public TextView pet_type;
    public ImageView photo_url;

    public PetHolder(@NonNull View itemView) {
        super(itemView);
        pet_name = itemView.findViewById(R.id.pet_name);
        pet_breed = itemView.findViewById(R.id.pet_breed);
        pet_type = itemView.findViewById(R.id.pet_type);
        photo_url = itemView.findViewById(R.id.photo_url);

    }
}
