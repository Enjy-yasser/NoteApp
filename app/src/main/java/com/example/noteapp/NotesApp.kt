package com.example.noteapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.navigation.NoteNavHost

@Composable
fun NotesApp(navController: NavHostController= rememberNavController()){
    NoteNavHost(
        navController = navController,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NoteTopAppBar(
    title: String,
    canNavigateBack : Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior ?= null ,
    navigateUp : () ->Unit ={}
) {
    CenterAlignedTopAppBar(
        title={ Text(title) },

        scrollBehavior=scrollBehavior,
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack)
            {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor =MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary

        ))
}