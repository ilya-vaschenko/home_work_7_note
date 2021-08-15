package com.example.home_work_7_note;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class NoteMapping {
    public static class Fields {
        public final static String DATE = "date";
        public final static String TITLE = "title";
        public final static String DESCRIPTION = "description";
        public final static String LIKE = "like";
    }

    public static Note toCardData(String id, Map<String, Object> doc) {
        Timestamp timeStamp = (Timestamp) doc.get(Fields.DATE);
        Note answer = new Note((String) doc.get(Fields.TITLE),
                timeStamp.toDate(),
                (String) doc.get(Fields.DESCRIPTION));
        answer.setId(id);
        return answer;
    }

    public static Map<String, Object> toDocument(Note note) {
        Map<String, Object> answer = new HashMap<>();
        answer.put(Fields.TITLE, note.getName());
        answer.put(Fields.DESCRIPTION, note.getDescription());
        answer.put(Fields.DATE, note.getDate());
        return answer;
    }
}
