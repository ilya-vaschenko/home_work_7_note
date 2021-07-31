package com.example.home_work_7_note;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {
    private ArrayList<Note> notes;

    private boolean isLand = false;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        initView(view);
    }

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        createNote();
        for (int i = 0; i < notes.size(); i++) {
            TextView textView = new TextView(getContext());
            textView.setText(notes.get(i).getName());
            textView.setTextSize(25);
            linearLayout.addView(textView);

            final int finalI = i;
            textView.setOnClickListener(v -> showNote(notes.get(finalI)));
        }
    }

    private void showNote(Note note) {
        if (isLand) {
            showNoteLand(note);
        } else {
            showNotePort(note);
        }
    }

    public void createNote() {
        notes = new ArrayList<>();
        notes.add(new Note("Важно", "26.07.2021", "Поспать уже наконец так, чтоб выспаться!"));
        notes.add(new Note("Не очень важно", "26.07.2021", "Иди на улицу, там ЛЕТО"));
        notes.add(new Note("Не важно", "26.07.2021", "Что бы ты не делал! Будет по другому... "));
    }

    void showNoteLand(Note note) {
        Fragment noteFragment = NoteTextFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.landscapeContainer, noteFragment)
                .commit();
    }

    void showNotePort(Note note) {
        Fragment noteFragment = NoteTextFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerNoteList, noteFragment)
                .addToBackStack("NoteTextFragment")
                .commit();
    }
}