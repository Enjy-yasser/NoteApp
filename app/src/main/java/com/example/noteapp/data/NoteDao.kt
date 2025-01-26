package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Note)
    // suspend 3lshan database takes long time to execute f hy run on separate thread

    @Update
    suspend fun update(notes: Note)

    @Query("SELECT * FROM notes") // by select from esm el table
    fun getAllNotes(): Flow<List<Note>>

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * from notes WHERE id = :id")
    fun getNote(id: Int): Flow<Note>


}