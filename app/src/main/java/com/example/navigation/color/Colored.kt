package com.example.navigation.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Colored() {
    val viewModel = hiltViewModel<ColoredViewModel>()
    Column(
        modifier = Modifier
            .background(Color(viewModel.colorValue))
            .fillMaxSize()
            .padding(16.dp)
    ) {}
}