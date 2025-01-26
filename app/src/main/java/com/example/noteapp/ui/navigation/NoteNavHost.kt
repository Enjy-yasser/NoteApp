package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.composable
import com.example.noteapp.ui.home.HomeDestination
import com.example.noteapp.ui.home.HomeScreen
import com.example.noteapp.ui.note.NoteDetailDestination
import com.example.noteapp.ui.note.NoteDetailsScreen
import com.example.noteapp.ui.note.NoteEditDestination
import com.example.noteapp.ui.note.NoteEditScreen
import com.example.noteapp.ui.note.NoteEntryDestination
import com.example.noteapp.ui.note.NoteEntryScreen

@Composable
fun NoteNavHost(navController: NavHostController ,modifier: Modifier =Modifier) {
    NavHost(
        navController=navController,
        startDestination = HomeDestination.route,
        modifier=modifier,
    ) {
        composable (route= HomeDestination.route){
            HomeScreen(
                navigateToNoteEntry = { navController.navigate(NoteEntryDestination.route) },
                navigateToNoteUpdate = { noteId -> navController.navigate("item_edit/$noteId") }
            )
        }
        composable(route = NoteEntryDestination.route) {
            NoteEntryScreen(
                navigateBack = { navController.popBackStack() },
                navigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = NoteDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteDetailDestination.ITEMIDARG) {
                type = NavType.IntType
            })
        ) {
            NoteDetailsScreen(
                navigateToEditNote = { navController.navigate("${NoteEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = NoteEditDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            NoteEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}

