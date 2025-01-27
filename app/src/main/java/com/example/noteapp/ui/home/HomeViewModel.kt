package com.example.noteapp.ui.home

//import android.R.attr.id
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.domain.models.Note
//import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.domain.repository.OfflineNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import javax.inject.Inject
//
@HiltViewModel
class HomeViewModel @Inject constructor(
//class HomeViewModel (
   private val notesRepository : OfflineNoteRepository) : ViewModel() {
        val homeUiState : StateFlow<HomeUiState> =
            notesRepository.getAllNoteItem().map { HomeUiState(it) }
            .stateIn(
            scope = viewModelScope,
            started= SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue= HomeUiState()
    )
    fun deleteNote(id: Int) {
        viewModelScope.launch {
            notesRepository.deleteNote(id)
        }
    }
    companion object{
        private const val TIMEOUT_MILLIS=5_000L
    }


}


data class HomeUiState (val noteList: List<Note> = listOf())