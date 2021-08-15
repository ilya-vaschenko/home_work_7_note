package com.example.home_work_7_note;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NoteSourceImpl implements NoteSource {
    private Context context;
    private List<Note> notes;

    public NoteSourceImpl(Context context) {
        this.context = context;

    }


    @Override
    public NoteSource init(NotesSourceResponse notesSourceResponse){

//        notes = new ArrayList<>(Arrays.asList(
//                new Note(context.getResources().getString(R.string.name1),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description1)
//                ),
//                new Note(context.getResources().getString(R.string.name2),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description2)
//                ),
//                new Note(context.getResources().getString(R.string.name3),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description3)
//                ),
//                new Note(context.getResources().getString(R.string.name4),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description4)
//                ),
//                new Note(context.getResources().getString(R.string.name5),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description5)
//                ),
//                new Note(context.getResources().getString(R.string.name6),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description6)
//                ),
//                new Note(context.getResources().getString(R.string.name7),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description7)
//                ),
//                new Note(context.getResources().getString(R.string.name8),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description8)
//                ),
//                new Note(context.getResources().getString(R.string.name9),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description9)
//                ),
//                new Note(context.getResources().getString(R.string.name10),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description10)
//                ),
//                new Note(context.getResources().getString(R.string.name11),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description11)
//                ),
//                new Note(context.getResources().getString(R.string.name12),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description12)
//                ),
//                new Note(context.getResources().getString(R.string.name13),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description13)
//                ),
//                new Note(context.getResources().getString(R.string.name14),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description12)
//                ),
//                new Note(context.getResources().getString(R.string.name15),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description12)
//                ),
//                new Note(context.getResources().getString(R.string.name16),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description12)
//                ),
//                new Note(context.getResources().getString(R.string.name17),
//                        Calendar.getInstance().getTime(),
//                        context.getResources().getString(R.string.description12)
//                )));

        if (notesSourceResponse != null) {
            notesSourceResponse.initialized(this);
        }

        return this;
    }
//
//    @Override
//    public NoteSource init(NotesSourceResponse notesSourceResponse) {
//        return null;
//    }
@Override
public Note getNote(int position) {
    return notes.get(position);
}

    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public void deleteCardData(int position) {
        notes.remove(position);
    }

    @Override
    public void addCardData(Note note) {
        notes.add(note);
    }

    @Override
    public void updateCardData(int position, Note note) {
        notes.set(position, note);
    }

    @Override
    public void clearCardData() {
        notes.clear();
    }
}

