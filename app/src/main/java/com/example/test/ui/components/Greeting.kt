package com.example.test.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.test.ui.theme.TestTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var extraPadding = if (isExpanded) 72.dp else 0.dp

    Surface(
        modifier = modifier.padding(top = 24.dp),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(24.dp)
    ) {
        Row (
            modifier = modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(bottom = extraPadding),
                horizontalAlignment = Alignment.Start,
            ) {
                GreetingText(text = "Hello")
                GreetingText(text = "$name!")
            }
            ElevatedButton(
                onClick = {
                    isExpanded = !isExpanded
                }
            ) {
                Text(if (isExpanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        Greeting("Sascha")
    }
}