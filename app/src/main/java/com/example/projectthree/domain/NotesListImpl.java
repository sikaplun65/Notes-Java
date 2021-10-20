package com.example.projectthree.domain;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    @SuppressLint("SimpleDateFormat")
    public void sortFromOldToNewNotes() {
        Collections.sort(notesList, (o1, o2) -> {
            try {
                return new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o1.getCreateDate())
                        .compareTo(new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o2.getCreateDate()));
            } catch (ParseException e) {
                return 0;
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    public void sortFromNewToOldNotes() {
        Collections.sort(notesList, (o1, o2) -> {
            try {
                return new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o2.getCreateDate())
                        .compareTo(new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o1.getCreateDate()));
            } catch (ParseException e) {
                return 0;
            }
        });
    }


    @SuppressLint("SimpleDateFormat")
    public void sorByDateModifiedNotes() {
        Collections.sort(notesList, (o1, o2) -> {
            try {
                if (o1.getModifiedDate() != null && o2.getModifiedDate() != null) {
                    return new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o2.getModifiedDate())
                            .compareTo(new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").parse(o1.getModifiedDate()));
                }
                return o1.getModifiedDate() == null ? 1 : -1;

            } catch (ParseException e) {
                return 0;
            }

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
                Thread.sleep(1000);
                notesList.add(new NoteEntity("Заметка " + (i + 1), "Сайт рыбатекст поможет дизайнеру, верстальщику," +
                        " вебмастеру сгенерировать несколько абзацев более менее осмысленного текста"));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
