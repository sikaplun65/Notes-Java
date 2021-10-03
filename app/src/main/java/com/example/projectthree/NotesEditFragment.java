package com.example.projectthree;

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
import android.widget.EditText;

import com.example.projectthree.domain.NoteEntity;
import com.example.projectthree.domain.NotesList;
import com.example.projectthree.domain.NotesListImpl;

public class NotesEditFragment extends Fragment {
    private static final String ID_KEY = "ID_KEY";
    private EditText titleEditText;
    private EditText detailEditText;
    private NotesList notesList;
    private String noteId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_edit, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesList = NotesListImpl.getList();
        Bundle args = getArguments();
        assert args != null;
        noteId = args.getString(ID_KEY);

        setTextTitle(view,notesList.getNote(noteId));
        setTextDetail(view,notesList.getNote(noteId));
        editingAndSaveNote(view);
    }


    private void setTextTitle(View view, NoteEntity note) {
        titleEditText = view.findViewById(R.id.title_edit_text);
        titleEditText.setText(note.getTitle());
    }

    private void setTextDetail(View view, NoteEntity note) {
        detailEditText = view.findViewById(R.id.detail_edit_text);
        detailEditText.setText(note.getDetail());
    }

    private void editingAndSaveNote(View view) {
        view.findViewById(R.id.save_button).setOnClickListener(v->{

            notesList.getNote(noteId).setTitle(titleEditText.getText().toString());
            notesList.getNote(noteId).setDetail(detailEditText.getText().toString());

            titleEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {}
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    notesList.getNote(noteId).setTitle(titleEditText.getText().toString());
                }
            });

            detailEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {}
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    notesList.getNote(noteId).setTitle(detailEditText.getText().toString());
                }
            });

            if(titleEditText.length() == 0 && detailEditText.length() != 0){
                notesList.getNote(noteId).setTitle(detailEditText.getText().toString().substring(0,10));
            } else if(titleEditText.length() == 0 && detailEditText.length() == 0){
                notesList.removeNote(notesList.getNote(noteId));
            }
            requireActivity().onBackPressed();
        });
    }

    public static NotesEditFragment getNoteId(String id){
        NotesEditFragment fragment = new NotesEditFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID_KEY,id);
        fragment.setArguments(bundle);
        return fragment;
    }
}