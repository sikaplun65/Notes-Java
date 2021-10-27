package com.example.projectthree.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.projectthree.R
import com.example.projectthree.domain.App
import com.example.projectthree.domain.NoteEntity

class NotesEditFragment : Fragment() {
    private lateinit var titleEditText: EditText
    private lateinit var detailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var notesList: App
    private var noteId: String? = null
    private lateinit var tempTitle: String
    private lateinit var tempDetail: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tempTitle = ""
        tempDetail = ""
        titleEditText = view.findViewById(R.id.title_edit_text)
        detailEditText = view.findViewById(R.id.detail_edit_text)
        saveButton = view.findViewById(R.id.save_button)
        notesList = requireActivity().applicationContext as App

        val args = this.arguments
        if (args != null && args.containsKey(ID_KEY)) {
            noteId = args.getString(ID_KEY)
            fillTextTitleAndTextDetail(notesList.getNote(noteId))
        }
        setupListeners()
    }

    private fun fillTextTitleAndTextDetail(note: NoteEntity) {
        titleEditText.setText(note.title)
        detailEditText.setText(note.detail)
        tempTitle = note.title
        tempDetail = note.detail
    }

    private fun setupListeners() {
        setHints()

        titleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tempTitle = titleEditText.text.toString()
            }
        })
        detailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tempDetail = detailEditText.text.toString()
            }
        })

        saveButton.setOnClickListener { v: View? ->
            var isModifiedDate = true

            if (noteId == null) {
                createNote()
                isModifiedDate = false
            }

            if(isModifiedDate) {
                if (!tempTitle.equals(notesList.getNote(noteId).title) || !tempDetail.equals(
                        notesList.getNote(noteId).detail)) {
                    notesList.getNote(noteId).setModifiedDate()
                }
            }

            if (tempTitle.isNotEmpty()) {
                notesList.getNote(noteId).title = tempTitle
            }
            if (tempDetail.isNotEmpty()) {
                notesList.getNote(noteId).detail = tempDetail
            }

            if (titleEditText.length().equals(0) && !detailEditText.length().equals(0)) {
                notesList.getNote(noteId).title =
                    detailEditText.text.toString().substring(0, 10)
            } else if (isNoteBlank) {
                notesList.removeNote(notesList.getNote(noteId))
            }

            requireActivity().onBackPressed()
        }
    }

    private fun createNote() {
        val newNote = NoteEntity()
        notesList.addNote(newNote)
        noteId = newNote.id
    }

    val isNoteBlank: Boolean
        get() = titleEditText.length().equals(0) && detailEditText.length().equals(0)

    fun setHints() {
        if (titleEditText.text.toString().isEmpty()) {
            titleEditText.hint = "Заголовок"
        }
        if (detailEditText.text.toString().isEmpty()) {
            detailEditText.hint = "текст заметки"
        }
    }

    companion object {
        private val ID_KEY = "ID_KEY"
        fun create(id: String?): NotesEditFragment {
            val fragment = NotesEditFragment()
            val bundle = Bundle().apply { putString(ID_KEY, id) }
            bundle.also { fragment.arguments = it }
            return fragment
        }

        fun create(): NotesEditFragment {
            return NotesEditFragment()
        }
    }
}