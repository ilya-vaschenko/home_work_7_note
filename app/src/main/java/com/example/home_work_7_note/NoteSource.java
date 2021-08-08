package com.example.home_work_7_note;

public interface NoteSource {
    com.example.home_work_7_note.Note getNote(int position);

    int size();

    void deleteCardData(int position);

    void addCardData(com.example.home_work_7_note.Note note);

    void updateCardData(int Position, com.example.home_work_7_note.Note note);

    void clearCardData();
}
