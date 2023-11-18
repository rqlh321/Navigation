package com.example.navigation.second

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GreetingSecond() {
    val viewModel = hiltViewModel<GreetingSecondViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Hello on Second!")
        Button(onClick = viewModel::toThirdScreen) { Text("To third screen!") }
        Button(onClick = viewModel::toFirstScreen) { Text("To first screen!") }
        Button(onClick = viewModel::toColorScreen) { Text("To colored") }
    }
}