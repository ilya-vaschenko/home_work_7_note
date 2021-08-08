package com.example.home_work_7_note;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    private String name;
    private Date date;
    private String description;

    public Note(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    protected Note(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = new Date(in.readLong());
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Note() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(date.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
