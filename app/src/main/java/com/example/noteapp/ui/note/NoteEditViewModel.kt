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
class NoteEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, // da 3lshan a retrive note id from navigation
    private val noteRepository: NoteRepository //Provides access to the note database
) : ViewModel() {

    //current item ui state
    var noteUiState by mutableStateOf(NoteUiState())
        private set //prevent external modifications

    private val noteId: Int = checkNotNull(savedStateHandle[NoteEditDestination.ID])

    init {
        //launches a coroutine so that the data retrieval happens asynchronously
        // without blocking the UI
        viewModelScope.launch {
            noteUiState = noteRepository.getNoteStream(noteId)
                .filterNotNull() //ensures only valid notes are processed.
                .first() //retrive first note from stream
                .toNoteUiState(true) //convert to noteuistate
        }
    }
    /*
    Runs when the ViewModel is created.
       Starts a coroutine to fetch the note asynchronously.
       Retrieves the note as a Flow, ensuring it's not null.
       Takes the first valid note, converts it to NoteUiState, and updates noteUiState.
       Updates UI state, triggering UI recomposition in Compose.
     */

    //Update the note in the noteRepository law input valid w y save fy reposotiry
    suspend fun updateNote() {
        if (validateInput(noteUiState.noteDetails)) {
            noteRepository.updateNote(noteUiState.noteDetails.toItem())
        }
    }

    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState =
            NoteUiState(noteDetails = noteDetails, isEntryValid = validateInput(noteDetails))
    }
    // da 3lshan lazem myb2sh blank
    private fun validateInput(uiState: NoteDetails = noteUiState.noteDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && content.isNotBlank()
        }
    }
}
