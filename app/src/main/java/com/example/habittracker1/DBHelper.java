package com.example.habittracker1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habitTracker.db";
    private static final String TABLE_NAME = "habits";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "progress";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_2 + " TEXT, "
                + COL_3 + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addHabit(Habit habit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, habit.getName());
        contentValues.put(COL_3, habit.getProgress());

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }
    @SuppressLint("Range")
    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_1));
                String name = cursor.getString(cursor.getColumnIndex(COL_2));
                String progress = cursor.getString(cursor.getColumnIndex(COL_3));

                habits.add(new Habit(id, name, progress));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return habits;
    }
    @SuppressLint("Range")
    public Habit getHabit(int position) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToPosition(position)) {
            int id = cursor.getInt(cursor.getColumnIndex(COL_1));
            String name = cursor.getString(cursor.getColumnIndex(COL_2));
            String progress = cursor.getString(cursor.getColumnIndex(COL_3));

            cursor.close();
            db.close();
            return new Habit(id, name, progress);
        }
        cursor.close();
        db.close();
        return null;
    }

    public Habit getHabitById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Habit", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String habitName = cursor.getString(1);
            String habitProgress = cursor.getString(2);
            cursor.close();
            return new Habit(id, habitName, habitProgress);
        }

        return null;
    }
}