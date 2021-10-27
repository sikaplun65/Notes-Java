package com.example.projectthree.ui

import com.example.projectthree.ui.NoteAdapter.InteractionListener
import com.example.projectthree.domain.NoteEntity
import androidx.recyclerview.widget.RecyclerView
import com.example.projectthree.ui.NoteAdapter
import com.example.projectthree.domain.App
import android.os.Bundle
import com.example.projectthree.R
import androidx.annotation.RequiresApi
import android.os.Build
import android.view.ContextMenu.ContextMenuInfo
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import android.content.DialogInterface
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import java.lang.IllegalStateException

class NotesListFragment : Fragment(), InteractionListener {
    private var controller: Controller? = null
    private lateinit var selectedNotesItem: NoteEntity
    private var id: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter
    private lateinit var notesList: App

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = if (context is Controller) context else {
            throw IllegalStateException("must implement NotesListFragment.Controller")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesList = requireActivity().applicationContext as App
        adapter = NoteAdapter(notesList.notes, this)
        initializationAddNewNoteButton(view)
        initializationRecyclerView(view)
        registerForContextMenu(recyclerView)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.note_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_note -> deleteNoteAlertDialog()
            R.id.menu_edit_note -> onItemClick(selectedNotesItem)
            R.id.menu_share_note -> Toast.makeText(activity, "Пункт меню в разработке", Toast.LENGTH_SHORT).show()
        }
        return super.onContextItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteNoteAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.text_message_alert_dialog_delete_note)
            .setCancelable(false)
            .setPositiveButton(R.string.text_yes) { dialog: DialogInterface?, id: Int ->
                notesList.removeNote(selectedNotesItem)
                adapter.notifyDataSetChanged()
                Snackbar.make(requireView(), "Заметка \"" + selectedNotesItem.title + "\" удалена", Snackbar.LENGTH_LONG).show()
            }
            .setNegativeButton(R.string.text_no, null)
            .show()
    }

    private fun initializationAddNewNoteButton(view: View) {
        view.findViewById<View>(R.id.new_note_button)
            .setOnClickListener { v: View? -> controller?.startNotesCreateFragment() }
    }

    private fun initializationRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(view.context))
        recyclerView.setAdapter(adapter)
        adapter.setData(notesList.notes)
        adapter.setOnItemClickListener(object : InteractionListener {
            override fun OnItemShortClick(item: NoteEntity) {
                onItemClick(item)
            }

            override fun OnItemLongClick(item: NoteEntity): Boolean {
                selectedNotesItem = item
                return false
            }
        })
    }

    private fun onItemClick(note: NoteEntity?) {
        id = note?.id
        controller?.startNotesEditFragment(id)
    }

    override fun onDestroy() {
        controller = null
        super.onDestroy()
    }

    override fun OnItemShortClick(item: NoteEntity) {}
    override fun OnItemLongClick(item: NoteEntity): Boolean = false

    interface Controller {
        fun startNotesCreateFragment()
        fun startNotesEditFragment(id: String?)
    }
}