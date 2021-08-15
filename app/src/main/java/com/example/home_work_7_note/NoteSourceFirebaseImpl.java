package com.example.home_work_7_note;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteSourceFirebaseImpl implements NoteSource{
    public static final String COLLECTION = "NOTES";
    public static final String TAG = "NoteSourceFirebaseImpl";

    private FirebaseFirestore bd = FirebaseFirestore.getInstance();
    private CollectionReference collection = bd.collection(COLLECTION);

    private List<Note> notes = new ArrayList();

    @Override
    public NoteSource init(NotesSourceResponse notesSourceResponse) {

        collection.orderBy(NoteMapping.Fields.DATE, Query.Direction.DESCENDING).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot result = task.getResult();
                        if (result == null) {
                            return;
                        }

                        notes = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> doc = document.getData();
                            String id = document.getId();
                            Note cardData = NoteMapping.toCardData(id, doc);
                            notes.add(cardData);
                        }
                        Log.d(TAG, "success " + notes.size() + " qnt");
                        notesSourceResponse.initialized(NoteSourceFirebaseImpl.this);
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                })
                .addOnFailureListener(e -> Log.d(TAG, "get failed with ", e));
        return this;

    }

    @Override
    public Note getNote(int position) {
        return notes.get(position);
    }

    @Override
    public int size() {
        if (notes == null) {
            return 0;
        }

        return notes.size();
    }

    @Override
    public void deleteCardData(int position) {
        collection.document(notes.get(position).getId()).delete();
        notes.remove(position);
    }

    @Override
    public void addCardData(Note note) {
        collection.add(NoteMapping.toDocument(note)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                note.setId(documentReference.getId());
            }
        });
    }

    @Override
    public void updateCardData(int position, Note note) {
        String id = note.getId();
        collection.document(id).set(NoteMapping.toDocument(note));
    }

    @Override
    public void clearCardData() {
        for (Note note : notes) {
            collection.document(note.getId()).delete();
        }
        notes.clear();
    }
}
