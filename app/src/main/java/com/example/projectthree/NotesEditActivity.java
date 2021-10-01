package com.example.projectthree;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectthree.domain.NoteEntity;
import com.example.projectthree.domain.NotesList;
import com.example.projectthree.domain.NotesListImpl;

public class NotesEditActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText detailEditText;
    private NotesList notesList;
    private String noteId;


    public static void startNotesEditActivity(Context context,String id) {
        Intent intent = new Intent(context,NotesEditActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_edit);

        Intent intent = getIntent();
        noteId = intent.getStringExtra("id");
        notesList = NotesListImpl.getList();

        setTextTitle(notesList.getNote(noteId));
        setTextDetail(notesList.getNote(noteId));
        editingAndSaveNote();

    }


    private void setTextTitle(NoteEntity note) {
        titleEditText = findViewById(R.id.title_edit_text);
        titleEditText.setText(note.getTitle());
    }

    private void setTextDetail(NoteEntity note) {
        detailEditText= findViewById(R.id.detail_edit_text);
        detailEditText.setText(note.getDetail());
    }

    private void editingAndSaveNote() {
        findViewById(R.id.save_button).setOnClickListener(v->{

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

            if(titleEditText.length() == 0 && detailEditText.length() == 0){
                notesList.removeNote(notesList.getNote(noteId));
            }
            finish();
        });
    }

}