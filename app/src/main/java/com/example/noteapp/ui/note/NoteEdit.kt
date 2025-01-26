package com.example.noteapp.ui.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.NoteTopAppBar
import com.example.noteapp.R
import com.example.noteapp.ui.AppViewModelProvider
import com.example.noteapp.ui.navigation.NavigationDestination
import com.example.noteapp.ui.theme.NoteAppTheme
import kotlinx.coroutines.launch

object NoteEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val idRess = R.string.edit_note
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NoteEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(NoteEditDestination.idRess),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
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
                .fillMaxSize()
        ) {
            // Entry Form for Title and Content
            InputForm(
                noteDetails = viewModel.noteUiState.noteDetails,
                onValueChanged = viewModel::updateUiState
            )
            // Save Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateItem()
                        navigateBack()
                    }
                },
                enabled = viewModel.noteUiState.isEntryValid,
                modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(text = stringResource(R.string.save_action))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteEditScreenPreview() {
    NoteAppTheme {
        NoteEditScreen(navigateBack = { }, onNavigateUp = { })
    }
}
