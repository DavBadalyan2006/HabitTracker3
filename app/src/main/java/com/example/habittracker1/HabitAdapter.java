package com.example.habittracker1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {

    private List<Habit> habits;
    private final Context context;
    private final OnHabitClickListener onHabitClickListener;

    public HabitAdapter(List<Habit> habits, Context context, OnHabitClickListener onHabitClickListener) {
        this.habits = habits;
        this.context = context;
        this.onHabitClickListener = onHabitClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_habit, parent, false);
        return new ViewHolder(view, onHabitClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Habit habit = habits.get(position);
        holder.habitNameTextView.setText(habit.getName());
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView habitNameTextView;
        OnHabitClickListener onHabitClickListener;

        public ViewHolder(@NonNull View itemView, OnHabitClickListener onHabitClickListener) {
            super(itemView);
            habitNameTextView = itemView.findViewById(R.id.habit_name_text_view);
            this.onHabitClickListener = onHabitClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onHabitClickListener.onHabitClick(getAdapterPosition());
        }
    }

    public interface OnHabitClickListener {
        void onHabitClick(int position);
    }

    public void updateHabits(List<Habit> habits) {
        this.habits = habits;
        notifyDataSetChanged();
    }
}
