package com.example.projectthree.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectthree.R;
import com.example.projectthree.domain.App;
import com.example.projectthree.domain.NoteEntity;

public class NotesEditFragment extends Fragment {
    private static final String ID_KEY = "ID_KEY";
    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;
    private App notesList;
    private String noteId;
    private String tempTitle;
    private String tempDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_edit, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tempTitle = "";
        tempDetail = "";
        titleEditText = view.findViewById(R.id.title_edit_text);
        detailEditText = view.findViewById(R.id.detail_edit_text);
        saveButton = view.findViewById(R.id.save_button);
        notesList =(App) requireActivity().getApplicationContext();

        Bundle args = getArguments();
        if (args != null && args.containsKey(ID_KEY)) {
            noteId = args.getString(ID_KEY);
            fillTextTitleAndTextDetail(notesList.getNote(noteId));
        }
        setupListeners();
    }

    private void fillTextTitleAndTextDetail(NoteEntity note) {
        titleEditText.setText(note.getTitle());
        detailEditText.setText(note.getDetail());
    }

    private void setupListeners() {

        setHints();

        titleEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempTitle = titleEditText.getText().toString();
            }
        });

        detailEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempDetail = detailEditText.getText().toString();
            }
        });

        saveButton.setOnClickListener(v -> {
            if (noteId == null) {
                createNote();
            }
            if (tempTitle.length() != 0) {
                notesList.getNote(noteId).setTitle(tempTitle);
            }
            if (tempDetail.length() != 0) {
                notesList.getNote(noteId).setDetail(tempDetail);
            }
            if(!tempTitle.equals(notesList.getNote(noteId).getTitle()) || !tempDetail.equals(notesList.getNote(noteId).getDetail())){
                notesList.getNote(noteId).setModifiedDate();
            }


            if (titleEditText.length() == 0 && detailEditText.length() != 0) {
                notesList.getNote(noteId).setTitle(detailEditText.getText().toString().substring(0, 10));
            } else if (isNoteBlank()) {
                notesList.removeNote(notesList.getNote(noteId));
            }
            requireActivity().onBackPressed();
        });
    }

    private void createNote() {
        NoteEntity newNote = new NoteEntity();
        notesList.addNote(newNote);
        noteId = newNote.getId();
    }

    public static NotesEditFragment create(String id) {
        NotesEditFragment fragment = new NotesEditFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID_KEY, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static NotesEditFragment create() {
        return new NotesEditFragment();
    }

    public boolean isNoteBlank() {
        return titleEditText.length() == 0 && detailEditText.length() == 0;
    }

    public void setHints() {

        if (titleEditText.getText().toString().length() == 0) {
            titleEditText.setHint("Заголовок");
        }
        if (detailEditText.getText().toString().length() == 0) {
            detailEditText.setHint("текст заметки");
        }

    }
}