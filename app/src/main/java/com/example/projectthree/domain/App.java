package com.example.projectthree.domain;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class App extends Application{
    private NotesListImpl notesList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreate() {
        super.onCreate();
        notesList = NotesListImpl.INSTANCE.getNotesList();
    }

    public List<NoteEntity> getNotes() {
        return notesList.getNotes();
    }

    public NoteEntity getNote(String id) {
        return notesList.getNote(id);
    }

    public void addNote(NoteEntity note) {
        notesList.addNote(note);
    }

    public void removeNote(NoteEntity note) {
        notesList.removeNote(note);
    }

    public void sortFromOldToNewNotes(){
        notesList.sortFromOldToNewNotes();
    }

    public void sortFromNewToOldNotes(){ notesList.sortFromNewToOldNotes(); }

    public void sortByModifiedDateNote(){notesList.sorByDateModifiedNotes();}
}
