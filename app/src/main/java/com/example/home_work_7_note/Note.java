package com.example.home_work_7_note;

public class Note {
    private final String name;
    private final String date;
    private final String description;

    public Note(String name, String date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
