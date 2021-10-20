package com.example.projectthree;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.projectthree.ui.NotesEditFragment;
import com.example.projectthree.ui.NotesListFragment;
import com.example.projectthree.ui.SettingsFragment;
import com.example.projectthree.ui.SortNotesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNotesListFragment(savedInstanceState);
        initBottomNavigationMenu();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, R.string.text_application_has_terminated, Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    private void initNotesListFragment(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NotesListFragment(), "NOTES_LIST_FRAGMENT")
                .commit();

    }

    private void addFragment(Fragment f) {
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

    @SuppressLint("NonConstantResourceId")
    private void initBottomNavigationMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.setting_notes_nav_menu:
                    Fragment filesFragment = getSupportFragmentManager().findFragmentByTag("setting_notes_fragment");
                    if (filesFragment == null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new SettingsFragment(), "setting_notes_fragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                case R.id.sort_notes_nav_menu:
                    Fragment settingsFragment = getSupportFragmentManager().findFragmentByTag("sort_notes_fragment");
                    if (settingsFragment == null) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new SortNotesFragment(), "sort_notes_fragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
            }
            return false;
        });
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
