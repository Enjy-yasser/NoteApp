package com.example.noteapp.ui.navigation

import androidx.navigation.NavHostController
import com.example.noteapp.ui.note.NoteEditDestination
import com.example.noteapp.ui.note.NoteEntryDestination
import javax.inject.Inject

//class NavigationManager{
class NavigationManager @Inject constructor() {
    fun navigateToNoteEntry(navController: NavHostController) {
        navController.navigate(NoteEntryDestination.route)
    }

    fun navigateToNoteUpdate(navController: NavHostController, noteId: Int) {
        navController.navigate("${NoteEditDestination.route}/$noteId")
    }

    fun navigateBack(navController: NavHostController) {
        navController.popBackStack()
    }

    fun navigateUp(navController: NavHostController) {
        navController.navigateUp()
    }
}
