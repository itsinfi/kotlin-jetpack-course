package org.study_iu.noto

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.Button
import org.study_iu.noto.ui.theme.NotoTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.platform.LocalContext


class ViewNotes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val note = intent.getStringExtra("note") ?: "Keine Notiz übergeben"
        setContent {
            NotoTheme {
                ViewNote(note = note)
            }
        }
    }

    @Composable
    fun ViewNote(note: String, modifier: Modifier = Modifier) {
        val activity = LocalActivity.current

        Scaffold(
            floatingActionButton = {
                ElevatedButton(onClick = { activity?.finish() }) {
                    Text("Go Back")
                }
            }
        ) { innerPadding ->
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 8.dp, end = 8.dp)
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Notiz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = note,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun NoteCardPreview() {
        ViewNote(note = "Dies ist eine Beispielnotiz.")
    }

}

