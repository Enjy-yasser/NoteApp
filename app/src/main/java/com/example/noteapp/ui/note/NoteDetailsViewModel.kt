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

@HiltViewModel
//class NoteDetailsViewModel ( savedStateHandle: SavedStateHandle,
class NoteDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
     private val noteRepository: NoteRepository) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[NoteDetailDestination.ITEMIDARG])

    val uiState: StateFlow<NoteDetailsUiState> =
        noteRepository.getNoteStream(noteId)
            .filterNotNull()
            .map {
                NoteDetailsUiState(noteDetails = it.toNoteDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NoteDetailsUiState()
            )

    suspend fun deleteNote(noteId:Int) {
        noteRepository.deleteNote(noteId)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

