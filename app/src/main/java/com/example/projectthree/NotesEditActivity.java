package com.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NotesEditActivity extends AppCompatActivity {


    public static void startNotesEditActivity(Context context) {
        Intent intent = new Intent(context,NotesEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_edit);
    }
}