package com.example.test.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.theme.TestTheme

@Composable
fun GreetingText(text: String) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Left,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingTextPreview() {
    TestTheme {
        GreetingText("Hello")
    }
}