package com.example.projectthree.domain;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

public class NotesListImpl implements NotesList{

    private static NotesListImpl instance;
    private final List<NoteEntity> notesList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotesListImpl(){
        notesList = new ArrayList<>();
        filListOfNotesWithTestValues();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotesListImpl getInstance(){
        if(instance == null) {
            instance = new NotesListImpl();
        }
        return instance;
    }

    @Override
    public List<NoteEntity> getNotes() {
        return notesList;
    }

    @Override
    public NoteEntity getNote(String id) {
        NoteEntity note = null;
        for(int i = 0; i < notesList.size(); i++){
            if(notesList.get(i).getId().equals(id)) {
                note = notesList.get(i);
            }
        }
        return note;
    }

    @Override
    public void addNote(NoteEntity note) {
        notesList.add(note);
    }

    @Override
    public void removeNote(NoteEntity note) {
        notesList.remove(note);
    }

    private void filListOfNotesWithTestValues(){
        int numberOfNotes = 6;
        for(int i = 0; i < numberOfNotes; i++){
            notesList.add(new NoteEntity("Заметка " + (i+1), "Сайт рыбатекст поможет дизайнеру, верстальщику," +
                    " вебмастеру сгенерировать несколько абзацев более менее осмысленного текста"));
        }
    }
}
