package com.example.projectthree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.projectthree.domain.NoteEntity;
import com.example.projectthree.domain.NotesList;
import com.example.projectthree.domain.NotesListImpl;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NotesListFragment())
                .commit();
    }

    public void startNotesEditFragment(String id) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NotesEditFragment.getNoteId(id))
                .addToBackStack(null)
                .commit();
    }
}
