package com.example.habittracker1;

public class Habit {
    private int id;
    private String name;
    private String progress;

    public Habit(int id, String name, String progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
