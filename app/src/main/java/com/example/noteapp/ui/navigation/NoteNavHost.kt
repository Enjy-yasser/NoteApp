package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.example.noteapp.ui.home.HomeDestination
import com.example.noteapp.ui.home.HomeScreen
import com.example.noteapp.ui.home.HomeViewModel
import com.example.noteapp.ui.note.NoteDetailDestination
import com.example.noteapp.ui.note.NoteDetailsScreen
import com.example.noteapp.ui.note.NoteDetailsViewModel
//import com.example.noteapp.ui.note.NoteDetailsViewModel
import com.example.noteapp.ui.note.NoteEditDestination
import com.example.noteapp.ui.note.NoteEditScreen
import com.example.noteapp.ui.note.NoteEditViewModel
//import com.example.noteapp.ui.note.NoteEditViewModel
import com.example.noteapp.ui.note.NoteEntryDestination
import com.example.noteapp.ui.note.NoteEntryScreen
import com.example.noteapp.ui.note.NoteEntryViewModel

//import com.example.noteapp.ui.note.NoteEntryViewModel

// manage navigation logic and set up navigation routes
@Composable
fun NoteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigationManager: NavigationManager = NavigationManager() // Inject NavigationManager
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier,
    ) {
        //composable route 3lshan kol screen in compose app 3obara 3n composable function
        // 3lshan keda doesnt require fragement aw activity
        composable(route = HomeDestination.route) {
            //tab3an injecting ma3 hilt hy provide required dependency repository
            val homeViewModel: HomeViewModel = hiltViewModel()
            //inject viewmodel for each screen
            // 3lshan kol screen leha its own data w viewmodel by store w mange ui related data independelty
//            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                navigateToNoteEntry = { navigationManager.navigateToNoteEntry(navController) },
                navigateToNoteUpdate = { noteId -> navigationManager.navigateToNoteUpdate(navController, noteId) }
            )
        }
        composable(route = NoteEntryDestination.route) {
            val noteEntryViewModel: NoteEntryViewModel = hiltViewModel()
            NoteEntryScreen(
                viewModel = noteEntryViewModel,
                navigateBack = { navigationManager.navigateBack(navController) },
                navigateUp = { navigationManager.navigateUp(navController) }
            )
        }
        composable(
            route = NoteDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteDetailDestination.ITEMIDARG) {
                type = NavType.IntType
            })
        ) {
            val noteDetailsViewModel: NoteDetailsViewModel = hiltViewModel()
            NoteDetailsScreen(
                viewModel = noteDetailsViewModel,
                navigateToEditNote = { noteId -> navigationManager.navigateToNoteUpdate(navController, noteId) },
                navigateBack = { navigationManager.navigateUp(navController) }
            )
        }
        composable(
            route = NoteEditDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteEditDestination.ID) {
                type = NavType.IntType
            })
        ) {
            val noteEditViewModel: NoteEditViewModel = hiltViewModel()
            NoteEditScreen(
                viewModel = noteEditViewModel,
                navigateBack = { navigationManager.navigateBack(navController) },
                onNavigateUp = { navigationManager.navigateUp(navController) }
            )
        }
    }
}
