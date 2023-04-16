package com.example.habittracker1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HabitDetailsActivity extends AppCompatActivity {
    private EditText habitNameEditText;
    private EditText habitDateEditText;
    private Button saveHabitButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);

        habitNameEditText = findViewById(R.id.habit_name_edit_text);
        habitDateEditText = findViewById(R.id.habit_date_edit_text);
        saveHabitButton = findViewById(R.id.save_habit_button);
        dbHelper = new DBHelper(HabitDetailsActivity.this);

        saveHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Habit habit = new Habit(-1, habitNameEditText.getText().toString(), habitDateEditText.getText().toString());
                dbHelper.addHabit(habit);
                finish();
            }
        });
    }
}
