package com.example.test.ui.components

import OnboardingScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test.ui.theme.TestTheme

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("Kotlin", "Jetpack Compose", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
) {

    var shoulShowOnboarding by remember { mutableStateOf(true) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (shoulShowOnboarding) {
            OnboardingScreen()
        } else {
            Column (modifier = modifier.padding(horizontal = 24.dp)) {
                for (name in names) {
                    Greeting(
                        name = name,
                        modifier = modifier.padding(innerPadding).fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    TestTheme {
        MyApp()
    }
}