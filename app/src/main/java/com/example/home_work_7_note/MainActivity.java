package com.example.home_work_7_note;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    NoteSource noteSource;
    private Navigation navigation;
    private com.example.home_work_7_note.Publisher publisher = new com.example.home_work_7_note.Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        noteSource = new NoteSourceImpl(this);
        navigation = new Navigation(getSupportFragmentManager());

        getNavigation().addFragment(NoteFragment.newInstance(), false);
        initView();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerNoteList, NoteListFragment.newInstance(noteSource))
                .commit();
 }

    private void initView() {
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public com.example.home_work_7_note.Publisher getPublisher() {
        return publisher;
    }

}