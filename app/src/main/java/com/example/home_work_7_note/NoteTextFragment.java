package com.example.home_work_7_note;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NoteTextFragment extends Fragment {
    private boolean isLand = false;

    private static final String ARG_NAME = "name";
    private static final String ARG_DATE = "date";
    private static final String ARG_DESCRIPTION = "description";

    Note note;
    private TextView textViewNoteName;
    private TextView textViewNoteDate;
    private TextView textViewNoteDescription;

    public NoteTextFragment() {
    }

    public static NoteTextFragment newInstance(Note note) {
        Bundle args = new Bundle();
        args.putString(ARG_NAME, note.getName());
        args.putString(ARG_DATE, note.getDate());
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
            String date = getArguments().getString(ARG_DATE);
            String description = getArguments().getString(ARG_DESCRIPTION);

            note = new Note(name, date, description);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_text, container, false);

        textViewNoteName = view.findViewById(R.id.textViewForName);
        textViewNoteDate = view.findViewById(R.id.textViewForDate);
        textViewNoteDescription = view.findViewById(R.id.textViewForDesc);

        isLand = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_back) {
            requireActivity().getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateText();
    }

    private void updateText() {
        textViewNoteName.setText(note.getName());
        textViewNoteDate.setText(note.getDate());
        textViewNoteDescription.setText(note.getDescription());
    }
}