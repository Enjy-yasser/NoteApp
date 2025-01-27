package com.example.noteapp.ui.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//class NoteEditViewModel( savedStateHandle: SavedStateHandle,
class NoteEditViewModel @Inject constructor( savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {

    //current item ui state
    var noteUiState by mutableStateOf(NoteUiState())
        private set

    private val noteId: Int = checkNotNull(savedStateHandle[NoteEditDestination.ID])

    init {
        viewModelScope.launch {
            noteUiState = noteRepository.getNoteStream(noteId)
                .filterNotNull()
                .first()
                .toNoteUiState(true)
        }
    }

    //Update the note in the noteRepository
    suspend fun updateNote() {
        if (validateInput(noteUiState.noteDetails)) {
            noteRepository.updateNote(noteUiState.noteDetails.toItem())
        }
    }

    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState =
            NoteUiState(noteDetails = noteDetails, isEntryValid = validateInput(noteDetails))
    }

    private fun validateInput(uiState: NoteDetails = noteUiState.noteDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && content.isNotBlank()
        }
    }
}
