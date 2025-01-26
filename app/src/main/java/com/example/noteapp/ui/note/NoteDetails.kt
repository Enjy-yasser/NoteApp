package com.example.noteapp.ui.note

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.NoteTopAppBar
import com.example.noteapp.R
import com.example.noteapp.ui.AppViewModelProvider
import com.example.noteapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object NoteDetailDestination : NavigationDestination {
    override val route = "note_details"
    override val idRess = R.string.note_details
    const val ITEMIDARG = "itemId"
    val routeWithArgs = "$route/{$ITEMIDARG}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    navigateToEditNote: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(NoteDetailDestination.idRess),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditNote(uiState.value.noteDetails.id.toString()) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(
                    end = WindowInsets.safeDrawing.asPaddingValues()
                        .calculateEndPadding(LocalLayoutDirection.current)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_note)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
        ) {
            val note = uiState.value.noteDetails
            NoteDetails(note = note, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.deleteNote(note.id)
                        navigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.delete))
            }
        }
    }
}

@Composable
fun NoteDetails(note: NoteDetails, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            NoteDetailRow(
                labelResId = R.string.note_title,
                noteDetail = note.title
            )
            NoteDetailRow(
                labelResId = R.string.note_content,
                noteDetail = note.content
            )
        }
    }
}

@Composable
private fun NoteDetailRow(
    @StringRes labelResId: Int,
    noteDetail: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
    ) {
        Text(text = stringResource(labelResId), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = noteDetail, style = MaterialTheme.typography.bodyMedium)
    }
}

