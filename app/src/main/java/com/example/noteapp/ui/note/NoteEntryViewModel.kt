package com.example.noteapp.ui.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NoteRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.noteapp.data.Note
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject


/*
* Viewmodel to valdiate and insert in database
*  */
//@HiltViewModel
class NoteEntryViewModel(
//class NoteEntryViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    //current ui state
    var noteUiState by mutableStateOf(NoteUiState())
        private set

    //update ui state
    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState =
            NoteUiState(noteDetails = noteDetails, isEntryValid = validateInput(noteDetails))
    }

    suspend fun saveNote() {
        if (validateInput()) {
            noteRepository.insertNote(noteUiState.noteDetails.toItem())
        }
    }
    suspend fun deleteNote() {
        if (validateInput()) {
            noteRepository.insertNote(noteUiState.noteDetails.toItem())
        }
    }

    private fun validateInput(uiState: NoteDetails = noteUiState.noteDetails): Boolean {
        return uiState.title.isNotBlank() && uiState.content.isNotBlank()

    }
}

data class NoteUiState(
    val noteDetails: NoteDetails = NoteDetails(),
    val isEntryValid:Boolean=false
)
data class NoteDetails(
    val id: Int = 0,
    val title: String = "",
    val content: String = "")
/*
Extension function to convert uistate ...> note
 */
fun NoteDetails.toItem(): Note = Note(
    id = id,
    title = title ,
    content=content
)
/*
Extension function to convert note ...> uiState
 */

fun Note.toNoteUiState(isEntryValid: Boolean = false): NoteUiState = NoteUiState(
    noteDetails = this.toNoteDetails(),
    isEntryValid = isEntryValid
)

    fun Note.toNoteDetails(): NoteDetails = NoteDetails(
        id = id,
        title = title ,
        content=content
    )


