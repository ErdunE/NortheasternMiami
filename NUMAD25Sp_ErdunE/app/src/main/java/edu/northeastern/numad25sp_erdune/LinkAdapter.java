package edu.northeastern.numad25sp_erdune;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {

    private final List<String> linkList;
    private final OnLinkLongClickListener longClickListener;

    public LinkAdapter(List<String> linkList, OnLinkLongClickListener longClickListener) {
        this.linkList = linkList;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String link = linkList.get(position);
        holder.linkTextView.setText(link);

        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onLinkLongClick(position, link);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return linkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView linkTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linkTextView = itemView.findViewById(android.R.id.text1);
        }
    }

    public interface OnLinkLongClickListener {
        void onLinkLongClick(int position, String link);
    }
}