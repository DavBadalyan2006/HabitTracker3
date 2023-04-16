package com.example.habittracker1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class HabitProgressActivity extends AppCompatActivity {
    private TextView habitNameTextView;
    private TextView habitProgressTextView;
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_progress);

        habitNameTextView = findViewById(R.id.habit_name_text_view);
        habitProgressTextView = findViewById(R.id.habit_progress_text_view);
        dbHelper = new DBHelper(HabitProgressActivity.this);

        int habitId = getIntent().getIntExtra("habitId", -1);
        Habit habit = dbHelper.getHabitById(habitId);

        habitNameTextView.setText(habit.getName());
        habitProgressTextView.setText(habit.getProgress());
    }
}

