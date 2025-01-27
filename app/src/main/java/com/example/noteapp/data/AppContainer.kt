//package com.example.noteapp.data
//
//import android.content.Context
//import com.example.noteapp.domain.repository.NoteRepository
//import com.example.noteapp.domain.repository.OfflineNoteRepository
//
//interface AppContainer {
//    val notesRepository: NoteRepository
//}
//
//class AppDataContainer(private val context: Context) : AppContainer {
//
//    override val notesRepository: NoteRepository by lazy {
//        OfflineNoteRepository(NoteDatabase.getDatabase(context).noteDao())
//
//    }
//}
