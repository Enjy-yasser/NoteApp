package com.example.noteapp.ui.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.NoteTopAppBar
import com.example.noteapp.R
//import com.example.noteapp.ui.AppViewModelProvider
import com.example.noteapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object NoteEntryDestination : NavigationDestination {
    override val route = "note_entry"
    override val idRess = R.string.entry_screen
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEntryScreen(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: NoteEntryViewModel = hiltViewModel()
//    viewModel: NoteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(R.string.add_screen),
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            InputForm(
                noteDetails = viewModel.noteUiState.noteDetails,
                onValueChanged = viewModel::updateUiState
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            // Save Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveNote()
                        navigateBack()
                    }
                },
                enabled = viewModel.noteUiState.isEntryValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(text = stringResource(R.string.save_action))
            }
        }
    }
}

@Composable
fun InputForm(
    noteDetails: NoteDetails,
    modifier: Modifier = Modifier,
    onValueChanged: (NoteDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = noteDetails.title,
            onValueChange = {
                onValueChanged(noteDetails.copy(title = it))
            },
            label = { Text(stringResource(R.string.note_title)) },
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
            enabled = enabled,
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )
        OutlinedTextField(
            value = noteDetails.content,
            onValueChange = {
                onValueChanged(noteDetails.copy(content = it))
            },
            label = { Text(stringResource(R.string.note_content)) },
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
            enabled = enabled,
            singleLine = false,
            shape = MaterialTheme.shapes.medium // Rounded corners
        )
    }
}