package com.example.noteapp.domain.repository

import com.example.noteapp.domain.models.Note
import com.example.noteapp.data.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//consistent API 3lshan a interact ma3 note data ba3edan ya3ny heya remote wla ofline
//class OfflineNoteRepository (private val noteDao: NoteDao) : NoteRepository{
/*
not supported by latest version kapt w lazem version kotlin a2al mn 2
 */
//@Singleton daaaaaaaaaaaaaaaaaaaaaaaaaaaaaa eeeerrrrrrrrrrrorrrrrrrrrrrrrrr

//w: Kapt currently doesn't support language version 2.0+. Falling back to 1.9.

class OfflineNoteRepository @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override fun getAllNoteItem(): Flow<List<Note>> =noteDao.getAllNotes()
    override fun getNoteStream(id: Int): Flow<Note?> =noteDao.getNote(id)

    override suspend fun insertNote(note: Note) =noteDao.insert(note)
    override suspend fun deleteNote(id: Int) =noteDao.delete(id)
    override suspend fun updateNote(note: Note) =noteDao.update(note)
}