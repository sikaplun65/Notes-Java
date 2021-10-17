package com.example.projectthree;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectthree.domain.NoteEntity;
import com.example.projectthree.domain.NotesList;
import com.example.projectthree.domain.NotesListImpl;
import com.google.android.material.snackbar.Snackbar;

public class NotesListFragment extends Fragment implements NoteAdapter.InteractionListener{
    private Controller controller;
    private NotesList notesList;
    private NoteEntity selectedNotesItem;
    private String id;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;


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
        adapter = new NoteAdapter(notesList.getNotes(), this);

        initializationAddNewNoteButton(view);
        initializationRecyclerView(view);

        registerForContextMenu(recyclerView);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.note_context_menu, menu);
    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_note:
                deleteNoteAlertDialog();
                break;
            case R.id.menu_edit_note:
                onItemClick(selectedNotesItem);
                break;
            case R.id.menu_share_note:
                Toast.makeText(getActivity(),"Пункт меню в разработке",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteNoteAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setMessage(R.string.text_message_alert_dialog_delete_note)
                .setCancelable(false)
                .setPositiveButton(R.string.text_yes, (dialog, id) -> {
                    notesList.removeNote(selectedNotesItem);
                    adapter.notifyDataSetChanged();
                    Snackbar.make(getView(),"Заметка \""+selectedNotesItem.getTitle()+"\" удалена",Snackbar.LENGTH_LONG).show();
                })
                .setNegativeButton(R.string.text_no, null)
                .show();
    }


    private void initializationAddNewNoteButton(View view) {
        view.findViewById(R.id.new_note_button).setOnClickListener(v -> {
            controller.startNotesCreateFragment();
        });
    }

    private void initializationRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setData(notesList.getNotes());
        adapter.setOnItemClickListener(new NoteAdapter.InteractionListener() {
            @Override
            public void OnItemShortClick(NoteEntity item) {
                onItemClick(item);
            }

            @Override
            public boolean OnItemLongClick(NoteEntity item) {
                selectedNotesItem = item;
                return false;
            }
        });
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

    @Override
    public void OnItemShortClick(NoteEntity item) {}

    @Override
    public boolean OnItemLongClick(NoteEntity item) { return false; }
}