package org.study_iu.noto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.study_iu.noto.ui.theme.NotoTheme
import org.study_iu.noto.MainActivity

/**
 * Asks the user to create a note and saves it. The result is
 * returned as an intent to the previous activity.
 */
class CreateNoteActivity : ComponentActivity() {

    /**
     * This function is executed once the activity is created.
     * Its purpose is to render the `CreateNoteForm` component
     *
     * @param savedInstanceState Bundle that contains the state of the previous activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotoTheme {
                CreateNoteForm()
            }
        }
    }
}

/**
 * This function saves the note and returns it
 * as a new intent to the previous activity.
 *
 * @param activity previous activity from context
 * @param note note that should be saved
 */
fun onSave(activity: Activity?, note: String) {
    val intent = Intent().apply {
        putExtra("note", note)
    }
    activity?.setResult(Activity.RESULT_OK, intent)
    activity?.finish()
}

/**
 * Component for the button that can be used to save the note.
 *
 * @param onClick callback for when the save button is pressed
 */
@Composable
fun SaveNoteButton(onClick: () -> Unit) {
    FloatingActionButton (
        onClick = onClick
    ) {
        Text(text = "Save")
    }
}

/**
 * Text field to enter the value of the note
 *
 * @param note current value of the note
 * @param onValueChange callback function that should be
 *                      executed once the value has been updated
 * @param label label that should be used for the text field
 * @param placeholder placeholder that should be used for the text field
 */
@Composable
fun CreateNoteTextField(
    note: String,
    onValueChange: (String) -> Unit,
    label: String = "Note",
    placeholder: String = "こんにちは、これはメモです。"
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp, max = 700.dp),
        onValueChange = onValueChange,
        value = note,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        maxLines = Int.MAX_VALUE,
        singleLine = false,
    )
}

/**
 * Component for a form that allows the user to enter a
 * note inside a text field and save it.
 */
@Composable
fun CreateNoteForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = context as? Activity

    var note by remember { mutableStateOf("") }

    Scaffold (
        floatingActionButton = {
            SaveNoteButton(onClick = { onSave(activity, note) })
        }
    ) { innerPadding ->
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 36.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 36.dp, bottom = 24.dp),
                text = "Create a note",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )

            CreateNoteTextField(
                note = note,
                onValueChange = { value -> note = value },
            )
        }
    }
}

/**
 * Preview for `CreateNoteForm`
 */
@Preview(showBackground = true)
@Composable
fun CreateNoteFormPreview() {
    NotoTheme {
        CreateNoteForm()
    }
}