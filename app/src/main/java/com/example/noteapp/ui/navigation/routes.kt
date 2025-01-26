package com.example.noteapp.ui.navigation

/**
 * Interface to describe the navigation destinations for the app
 */
interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String
    /**
     *id contains title to be displayed for the screen.
     */
    val titleRes: Int
}
