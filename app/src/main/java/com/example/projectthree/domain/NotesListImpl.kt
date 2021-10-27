package com.example.projectthree.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotesListImpl implements NotesList {

    private static NotesListImpl instance;
    private final List<NoteEntity> notesList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotesListImpl() {
        notesList = new ArrayList<>();
        filListOfNotesWithTestValues();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static NotesListImpl getInstance() {
        if (instance == null) {
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
        for (int i = 0; i < notesList.size(); i++) {
            if (notesList.get(i).getId().equals(id)) {
                note = notesList.get(i);
            }
        }
        return note;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortFromOldToNewNotes() {
        Collections.sort(notesList, (Comparator.comparing(NoteEntity::getCreateDate)));
    }

    public void sortFromNewToOldNotes() {
        Collections.sort(notesList, (o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
    }

    public void sorByDateModifiedNotes() {
        Collections.sort(notesList, (o1, o2) -> {
            if (o1.getModifiedDate() == null && o2.getModifiedDate() == null) {
                return 0;
            } else if (o1.getModifiedDate() == null) {
                return 1;
            } else if (o2.getModifiedDate() == null) {
                return -1;
            }
            return o2.getModifiedDate().compareTo(o1.getModifiedDate());
        });
    }

    @Override
    public void addNote(NoteEntity note) {
        notesList.add(note);
    }

    @Override
    public void removeNote(NoteEntity note) {
        notesList.remove(note);
    }

    private void filListOfNotesWithTestValues() {
        int numberOfNotes = 6;

        for (int i = 0; i < numberOfNotes; i++) {
            try {
                // задержка времени при создании для проверки сортировок
                Thread.sleep(10);
                notesList.add(new NoteEntity("Заметка " + (i + 1), "Сайт рыбатекст поможет дизайнеру, верстальщику," +
                        " вебмастеру сгенерировать несколько абзацев более менее осмысленного текста"));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
