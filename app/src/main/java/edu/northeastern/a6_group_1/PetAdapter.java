package edu.northeastern.a6_group_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetAdapter extends RecyclerView.Adapter<PetHolder> {

    private List<Pet> petList;
    private ClickListener listener;

    public PetAdapter(List<Pet> petList) {
        this.petList = petList;
    }

    public void OnItemClickListener(ClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pet_card, parent, false);
        return new PetHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = petList.get(position);

        // Set pet data into the views
        holder.pet_name.setText(pet.getName());
        holder.pet_breed.setText(pet.getBreed());
        holder.pet_type.setText(pet.getType());
        // Load the pet image
        Picasso.get().load(pet.getImageUrl()).into(holder.photo_url);
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}
