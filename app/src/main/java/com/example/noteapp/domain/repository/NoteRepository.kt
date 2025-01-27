package com.example.noteapp.domain.repository

import com.example.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getAllNoteItem (): Flow<List<Note>>
    fun getNoteStream(id: Int): Flow<Note?>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(note: Note)
}