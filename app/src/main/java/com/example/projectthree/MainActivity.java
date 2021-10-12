package com.example.projectthree;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new NotesListFragment())
                    .commit();
        }
    }

    private void addFragment(Fragment f){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, f)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, f)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void startNotesEditFragment(String id) {
        addFragment(NotesEditFragment.create(id));
    }

    @Override
    public void startNotesCreateFragment() {
        addFragment(NotesEditFragment.create());
    }

}
