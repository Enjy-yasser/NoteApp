//package com.example.noteapp.ui
//
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
//import androidx.lifecycle.createSavedStateHandle
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.noteapp.NotesApplication
//import com.example.noteapp.domain.repository.OfflineNoteRepository
//import com.example.noteapp.ui.home.HomeViewModel
//import com.example.noteapp.ui.note.NoteDetailsViewModel
//import com.example.noteapp.ui.note.NoteEditViewModel
//import com.example.noteapp.ui.note.NoteEntryViewModel
//
//// Factory to create instancf of viewmodel
//
//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//        initializer {
//            NoteEditViewModel(
//                this.createSavedStateHandle(),
//                noteApplication().container.notesRepository
//            )
//        }
//        initializer {
//            NoteEntryViewModel(noteApplication().container.notesRepository)
//        }
//        initializer {
//            NoteDetailsViewModel(
//                this.createSavedStateHandle(),
//                noteApplication().container.notesRepository
//            )
//        }
//        initializer {
//            HomeViewModel(noteApplication().container.notesRepository as OfflineNoteRepository) //casting 3lshan offlinenotesrepository
//        }
//    }
//}
//
//fun CreationExtras.noteApplication(): NotesApplication =
//    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)
