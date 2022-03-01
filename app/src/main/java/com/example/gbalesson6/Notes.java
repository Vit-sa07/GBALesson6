package com.example.gbalesson6;

import android.os.Parcel;
import android.os.Parcelable;


public class Notes implements Parcelable {
    private String heading;
    private String noteText;
    private String date;
    private int noteIndex;

    public Notes(String theme, String noteText, String date, int index) {
        this.heading = theme;
        this.noteText = noteText;
        this.date = date;
        this.noteIndex = index;

    }

    protected Notes(Parcel in) {
        heading = in.readString();
        noteText = in.readString();
        date = in.readString();
        noteIndex = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getHeading() {
        return heading;
    }

    public String getNoteText() {
        return noteText;
    }

    public String getDate() {
        return date;
    }

    public int getNoteIndex() {
        return noteIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(heading);
        dest.writeString(noteText);
        dest.writeString(date);
        dest.writeInt(noteIndex);
    }
}
