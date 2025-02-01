package edu.northeastern.numad25sp_erdune;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {

    private final List<String[]> linkList;
    private final OnLinkClickListener clickListener;
    private final OnLinkLongClickListener longClickListener;

    public LinkAdapter(List<String[]> linkList, OnLinkClickListener clickListener, OnLinkLongClickListener longClickListener) {
        this.linkList = linkList;
        this.clickListener = clickListener;
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
        String name = linkList.get(position)[0];
        String url = linkList.get(position)[1];
        holder.linkTextView.setText(name + ": " + url);

        holder.itemView.setOnClickListener(v -> clickListener.onLinkClick(url));
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onLinkLongClick(position, name, url);
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

    public interface OnLinkClickListener {
        void onLinkClick(String url);
    }

    public interface OnLinkLongClickListener {
        void onLinkLongClick(int position, String name, String url);
    }
}