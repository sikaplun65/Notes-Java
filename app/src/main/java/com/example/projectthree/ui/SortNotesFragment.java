package com.example.projectthree.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectthree.R;
import com.example.projectthree.domain.App;


public class SortNotesFragment extends Fragment {
    private App notesList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesList = (App) requireActivity().getApplicationContext();

        view.findViewById(R.id.sort_from_old_to_new_notes_button).setOnClickListener(v -> {
            notesList.sortFromOldToNewNotes();
            requireActivity().onBackPressed();
        });

        view.findViewById(R.id.sort_from_new_to_old_notes_button).setOnClickListener(v -> {
            notesList.sortFromNewToOldNotes();
            requireActivity().onBackPressed();
        });

        view.findViewById(R.id.sort1_by_modified_date_button).setOnClickListener(v -> {
            notesList.sortByModifiedDateNote();
            requireActivity().onBackPressed();
        });

    }
}