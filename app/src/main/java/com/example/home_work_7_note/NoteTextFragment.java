package com.example.home_work_7_note;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class NoteTextFragment extends Fragment {
    private boolean isLand = false;

    private static final String ARG_NAME = "name";
    private static final String ARG_DATE = "date";
    private static final String ARG_DESCRIPTION = "description";

    TextView add_note_name;
    TextView add_note_desc;
    Note note;

    private TextView textViewNoteName;
    private TextView textViewNoteDescription;

    public NoteTextFragment() {
    }

    public static NoteTextFragment newInstance(Note note) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, note.getName());
        args.putString(ARG_DATE, note.getDate().toString());
        args.putString(ARG_DESCRIPTION, note.getDescription());

        NoteTextFragment fragment = new NoteTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_NAME);
            String description = getArguments().getString(ARG_DESCRIPTION);

            note = new Note(name, Calendar.getInstance().getTime(), description);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_text, container, false);

        textViewNoteName = view.findViewById(R.id.edit_Text_For_Name);
        textViewNoteDescription = view.findViewById(R.id.edit_text_ForDesc);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        add_note_name = view.findViewById(R.id.edit_Text_For_Name);
        add_note_desc = view.findViewById(R.id.edit_text_ForDesc);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateText();
    }

    private void updateText() {
        textViewNoteName.setText(note.getName());
        textViewNoteDescription.setText(note.getDescription());
    }
}