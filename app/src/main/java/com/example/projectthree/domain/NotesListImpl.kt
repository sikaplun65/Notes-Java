package com.example.projectthree.domain

import java.util.*

object NotesListImpl : NotesList {

    private var notesList: MutableList<NoteEntity>

    init {
        notesList = mutableListOf()
        filListOfNotesWithTestValues()
    }

    fun getNotesList(): NotesListImpl {
        return NotesListImpl
    }

    override fun getNotes(): List<NoteEntity> = notesList

    override fun getNote(id: String): NoteEntity? = notesList.find { note -> note.id.equals(id) }

    fun sortFromOldToNewNotes() = notesList.sortBy { noteEntity -> noteEntity.createDate }

    fun sortFromNewToOldNotes() = notesList.sortByDescending { noteEntity -> noteEntity.createDate }

    fun sorByDateModifiedNotes() {
        val sortedListNotes = notesList.sortedWith(
            compareBy<NoteEntity, Calendar?>
                (nullsLast(reverseOrder()), { it.modifiedDate }),
        )
        notesList = sortedListNotes as MutableList<NoteEntity>
    }

    override fun addNote(note: NoteEntity) {
        notesList.add(note)
    }

    override fun removeNote(note: NoteEntity) {
        notesList.remove(note)
    }

    private fun filListOfNotesWithTestValues() {
        val numberOfNotes = 6
        for (i in 0 until numberOfNotes) {
            try {
                // задержка времени при создании для проверки сортировок
                Thread.sleep(10)
                notesList.add(
                    NoteEntity(
                        "Заметка " + (i + 1), "Сайт рыбатекст поможет дизайнеру, верстальщику," +
                                " вебмастеру сгенерировать несколько абзацев более менее осмысленного текста"
                    )
                )
            } catch (ex: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

}