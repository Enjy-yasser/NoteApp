package com.example.noteapp.data

import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject

//consistent API for interacting with note data regardless (offline-remote database)
class OfflineNoteRepository (private val noteDao: NoteDao) : NoteRepository{
//class OfflineNoteRepository @Inject constructor(private val noteDao: NoteDao) : NoteRepository{
    override fun getAllNoteItem(): Flow<List<Note>> =noteDao.getAllNotes()
    override fun getNoteStream(id: Int): Flow<Note?> =noteDao.getNote(id)

    override suspend fun insertNote(note: Note) =noteDao.insert(note)
    override suspend fun deleteNote(id: Int) =noteDao.delete(id)
    override suspend fun updateNote(note: Note) =noteDao.update(note)
}