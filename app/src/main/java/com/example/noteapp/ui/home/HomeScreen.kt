package com.example.noteapp.ui.home
//import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.domain.models.Note
import kotlin.collections.listOf
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
//import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.NoteTopAppBar
//import com.example.noteapp.ui.AppViewModelProvider
import com.example.noteapp.ui.navigation.NavigationDestination
import com.example.noteapp.ui.theme.NoteAppTheme
object HomeDestination : NavigationDestination {
    override val route = "home"
    override val idRess = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToNoteEntry : ()->Unit,
    navigateToNoteUpdate: (Int)->Unit,
    viewModel: HomeViewModel = hiltViewModel(),
//    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier= Modifier) {

    val homeUiState by viewModel.homeUiState.collectAsState()

    println("HomeUiState noteList size: ${homeUiState.noteList.size}")

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier=modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NoteTopAppBar(
                title = stringResource(HomeDestination.idRess),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNoteEntry,
                modifier = Modifier.padding(
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                        )
            ) { Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_item)
            ) }
        }

    ) {
        innerPadding -> HomeBody(
            noteList = homeUiState.noteList,
            onNoteClick = navigateToNoteUpdate,
            modifier= modifier.fillMaxSize(),
            contentPadding=innerPadding,
        )

    }
}

@Composable
fun HomeBody(
    noteList: List<Note>,
    onNoteClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(3.dp),
) {
    println("Recomposing HomeBody with ${noteList.size} notes")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (noteList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_notes_available),
                textAlign = TextAlign.Center,
                color =Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding)
            )

        } else {
            NoteList(
                noteList = noteList,
                onNoteClick = { onNoteClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun NoteList(
    noteList: List<Note>,
    onNoteClick: (Note) -> Unit,
    contentPadding:PaddingValues,
    modifier: Modifier= Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
//    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)

){
    val (noteToDelete, setNoteToDelete) =remember { mutableStateOf<Note?>(null) }
    LazyColumn(
        modifier=modifier,
        contentPadding=contentPadding
    ) {
        //note is each individual Note object as the list is iterated.
        items( items = noteList, key= { it.id } ){ note ->
            NoteItem(
                note = note,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .clickable { onNoteClick(note) },
                onDeleteClick = {setNoteToDelete(note)}
            )
        }
    }
    if (noteToDelete != null) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                viewModel.deleteNote(noteToDelete.id)
                setNoteToDelete(null)
            },
            onDeleteDismiss = { setNoteToDelete(null) }
        )
}

}
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDeleteDismiss() },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_question)) },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = onDeleteDismiss) {
                Text(text = stringResource(R.string.no))
            }
        },
        modifier = modifier
    )
}

@Composable
private fun NoteItem(note: Note, onDeleteClick:()->Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            // Title - display on top
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )

            // Content - display below the title
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row  {
            Spacer(modifier = Modifier.weight(1f))
            // Delete Button
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }
        }

}
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    NoteAppTheme {
        HomeBody(listOf(), onNoteClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreview() {
    NoteAppTheme {
        NoteItem(
            Note(2, "Enjyyy", "Yasserr"),
            onDeleteClick = {}
        )
    }
}
