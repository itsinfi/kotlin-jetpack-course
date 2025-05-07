package org.study_iu.noto

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.study_iu.noto.ui.theme.NotoTheme
import androidx.compose.material3.ElevatedButton
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.ui.unit.dp

/**
 * Main activity of the app
 */
class MainActivity : ComponentActivity() {
    private val notes = mutableStateListOf<String>()

    /**
     * Launcher function to launch new intents.
     * The results will be awaited and handled.
     */
    private val createNoteLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val note = result.data?.getStringExtra("note")

            if (!note.isNullOrBlank()) {
                notes.add(note)
            }
            println("Note received: $note")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotoTheme {
                MainScreen(
                    notes = notes,
                    onCreateNote = {
                        val intent = Intent(this, CreateNoteActivity::class.java)
                        createNoteLauncher.launch(intent)
                    },
                    onNoteClick = { note ->
                        val intent = Intent(this, ViewNotes::class.java)
                        intent.putExtra("note", note)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    notes: List<String>,
    onCreateNote: () -> Unit,
    onNoteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            ElevatedButton(onClick = onCreateNote) {
                Text("Create Note")
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NotesList(
            notes = notes,
            onNoteClick = onNoteClick,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun NotesList(
    notes: List<String>,
    onNoteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteCard(
                note = note,
                onClick = { onNoteClick(note) }
            )
        }
    }
}

@Composable
fun NoteCard(
    note: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = note,
            modifier = Modifier.padding(16.dp)
        )
    }
}



@Composable
fun Greeting(onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedButton(onClick = onClick) {
        Text(
            text = "Create Note",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotoTheme {
        Greeting({})
    }
}