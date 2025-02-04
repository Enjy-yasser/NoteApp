package com.example.noteapp.ui.note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.domain.repository.NoteRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.noteapp.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
* Viewmodel to valdiate and insert in database
*  */
@HiltViewModel
//class NoteEntryViewModel(
class NoteEntryViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    //current ui state
    var noteUiState by mutableStateOf(NoteUiState())
        private set //PREVENT OUTER MODIFICATIONS

    //update ui state when user enters data
    fun updateUiState(noteDetails: NoteDetails) {
        noteUiState =
            NoteUiState(noteDetails = noteDetails, isEntryValid = validateInput(noteDetails))
        //call validate input to check if input is valid
    }

    suspend fun saveNote() {
        if (validateInput()) {
            noteRepository.insertNote(noteUiState.noteDetails.toItem())
        }
    }

    private fun validateInput(uiState: NoteDetails = noteUiState.noteDetails): Boolean {
        return uiState.title.isNotBlank() && uiState.content.isNotBlank()

    }
}

data class NoteUiState(
    val noteDetails: NoteDetails = NoteDetails(), // actual note content
    val isEntryValid:Boolean=false //whether the input is valid
)
data class NoteDetails(
    val id: Int = 0,
    val title: String = "",
    val content: String = "")
/*
Extension function to convert noteDetails ...> Note
3lshan UI and database models are separate.
 */
fun NoteDetails.toItem(): Note = Note(
    id = id,
    title = title ,
    content=content
)
/*
Extension function to convert note ...> NoteUiState
lama a load note mn database ll ui
 */

fun Note.toNoteUiState(isEntryValid: Boolean = false): NoteUiState = NoteUiState(
    noteDetails = this.toNoteDetails(),
    isEntryValid = isEntryValid
)
/*
Extracts only the important details from a Note.
Lama a display only the note details in the UI.
 */
    fun Note.toNoteDetails(): NoteDetails = NoteDetails(
        id = id,
        title = title ,
        content=content
    )

/*
toItem()	Converts UI note to database note	NoteDetails → Note
toNoteUiState()	Converts database note to UI state	Note → NoteUiState
toNoteDetails()	Extracts note details for UI	Note → NoteDetails
 */
