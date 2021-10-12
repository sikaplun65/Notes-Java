package com.example.projectthree;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectthree.domain.NoteEntity;
import com.example.projectthree.domain.NotesList;
import com.example.projectthree.domain.NotesListImpl;

public class NotesListFragment extends Fragment {
    private Controller controller;
    private NotesList notesList;
    private NoteAdapter adapter = new NoteAdapter();
    private String id;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("must implement NotesListFragment.Controller");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesList = NotesListImpl.getList();

        initializationAddNewNoteButton(view);
        initializationRecyclerView(view);

    }

    private void initializationAddNewNoteButton(View view) {
        view.findViewById(R.id.new_note_button).setOnClickListener(v ->{
            controller.startNotesCreateFragment();
        });
    }

    private void initializationRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setData(notesList.getNotes());
        adapter.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(NoteEntity note) {
        id = note.getId();
        controller.startNotesEditFragment(id);
    }

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }

    interface Controller {
        void startNotesCreateFragment();
        void startNotesEditFragment(String id);
    }
}