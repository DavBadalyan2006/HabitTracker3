package com.example.habittracker1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addHabitButton;
    private RecyclerView habitRecyclerView;
    private DBHelper dbHelper;
    private HabitAdapter habitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHabitButton = findViewById(R.id.add_habit_button);
        habitRecyclerView = findViewById(R.id.habit_recycler_view);
        dbHelper = new DBHelper(MainActivity.this);

        List<Habit> habitList = dbHelper.getAllHabits();

        habitAdapter = new HabitAdapter(new ArrayList<>(), this, position -> {
            Habit habit = dbHelper.getHabit(position);
            Intent intent = new Intent(MainActivity.this, HabitDetailsActivity.class);
            intent.putExtra("Habit", (CharSequence) habit);
            startActivity(intent);
        });
        habitRecyclerView.setAdapter(habitAdapter);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HabitDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Habit> habitList = dbHelper.getAllHabits();
        habitAdapter.updateHabits(habitList);
    }
}
