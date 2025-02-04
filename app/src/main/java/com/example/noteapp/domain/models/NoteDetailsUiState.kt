package com.example.noteapp.domain.models

import com.example.noteapp.ui.note.NoteDetails

/*
1- Model
Holds the UI state of a single note.
Defaults to an empty NoteDetails object.

 */

data class NoteDetailsUiState(
    val noteDetails: NoteDetails = NoteDetails()
)
// convert note object (database) l note details object
fun Note.toNoteDetails(): NoteDetails = NoteDetails(
    id = id,
    title = title ,
    content=content
)

