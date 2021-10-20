package com.example.projectthree.domain;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class App extends Application implements NotesList {
    private NotesListImpl notesList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreate() {
        super.onCreate();
        notesList = NotesListImpl.getInstance();
    }

    @Override
    public List<NoteEntity> getNotes() {
        return notesList.getNotes();
    }

    @Override
    public NoteEntity getNote(String id) {
        return notesList.getNote(id);
    }

    @Override
    public void addNote(NoteEntity note) {
        notesList.addNote(note);
    }

    @Override
    public void removeNote(NoteEntity note) {
        notesList.removeNote(note);
    }

    public void sortFromOldToNewNotes(){
        notesList.sortFromOldToNewNotes();
    }

    public void sortFromNewToOldNotes(){ notesList.sortFromNewToOldNotes(); }

    public void sortByModifiedDateNote(){notesList.sorByDateModifiedNotes();}
}
