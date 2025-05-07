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

/**
 * Main activity of the app
 */
class MainActivity : ComponentActivity() {

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
                // Starte ViewNotes und übergebe die Notiz
                val intent = Intent(this, ViewNotes::class.java)
                intent.putExtra("note", note)
                startActivity(intent)
            }
            println("Note received: $note")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        onClick = {
                            val intent = Intent(this, CreateNoteActivity::class.java)
                            createNoteLauncher.launch(intent)
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
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