package com.example.noteapp.domain.models

import com.example.noteapp.ui.note.NoteDetails

data class NoteDetailsUiState(
    val noteDetails: NoteDetails = NoteDetails()
)
fun Note.toNoteDetails(): NoteDetails = NoteDetails(
    id = id,
    title = title ,
    content=content
)

