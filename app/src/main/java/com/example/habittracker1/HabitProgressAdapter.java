package com.example.habittracker1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HabitProgressAdapter extends RecyclerView.Adapter<HabitProgressAdapter.HabitProgressViewHolder> {
    private List<String> progressList;

    public HabitProgressAdapter(List<String> progressList) {
        this.progressList = progressList;
    }

    @NonNull
    @Override
    public HabitProgressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new HabitProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitProgressViewHolder holder, int position) {
        String progress = progressList.get(position);
        holder.textView.setText(progress);
    }

    @Override
    public int getItemCount() {
        return progressList.size();
    }

    class HabitProgressViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        HabitProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
