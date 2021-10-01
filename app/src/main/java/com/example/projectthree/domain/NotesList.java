package com.example.projectthree.domain;

import android.text.TextWatcher;

import java.util.List;

public interface NotesList {
    List<NoteEntity> getNotes();

    NoteEntity getNote(String id);

    void addNote(NoteEntity note);

    void removeNote(NoteEntity note);
}
