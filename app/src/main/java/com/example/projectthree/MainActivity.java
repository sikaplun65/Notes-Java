package com.example.projectthree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initNotesListFragment(savedInstanceState);
        initBottomNavigationMenu();
    }

    private void initNotesListFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new NotesListFragment(), "NOTES_LIST_FRAGMENT")
                    .commit();
        }
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
                case R.id.folder_nav_menu:
                    Fragment filesFragment = getSupportFragmentManager().findFragmentByTag("files_fragment");
                    if(filesFragment == null){
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new FilesFragment(),"files_fragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    return true;
                case R.id.settings_nav_menu:
                    Fragment settingsFragment = getSupportFragmentManager().findFragmentByTag("setting_fragment");
                    if(settingsFragment == null){
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new SettingsFragment(),"setting_fragment")
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
