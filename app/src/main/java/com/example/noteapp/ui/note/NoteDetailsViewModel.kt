package com.example.noteapp.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.models.NoteDetailsUiState
import com.example.noteapp.domain.models.toNoteDetails
import com.example.noteapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
//ViewModel is managed by Hilt, which automatically injects dependencies.
@HiltViewModel
//class NoteDetailsViewModel ( savedStateHandle: SavedStateHandle,
class NoteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, // da 3lshan a retrive note id from navigation
     private val noteRepository: NoteRepository) : ViewModel() {
// Extracts note ID from navigation arguments.
    private val noteId: Int = checkNotNull(savedStateHandle[NoteDetailDestination.ITEMIDARG])

    val uiState: StateFlow<NoteDetailsUiState> =
        noteRepository.getNoteStream(noteId)
            .filterNotNull() // non-null values are processed.
            .map {
                NoteDetailsUiState(noteDetails = it.toNoteDetails()) //Converts the Note object into NoteDetailsUiState
            }.stateIn( //convert flow into stateflow
                scope = viewModelScope,//lifecycle-aware coroutine scope
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),//Starts when subscribed and stops after 5 seconds of inactivity
                initialValue = NoteDetailsUiState()
            )
//        suspend: Must be called from a coroutine.
    suspend fun deleteNote(noteId:Int) {
        noteRepository.deleteNote(noteId) //Deletes the note with the given ID from the repository.

    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

