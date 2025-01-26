package com.example.noteapp.data

import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getAllNoteItem (): Flow<List<Note>>
    fun getNoteStream(id: Int): Flow<Note?>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(noteId: Int)
    suspend fun updateNote(note: Note)
}