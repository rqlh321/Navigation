package com.example.navigation.color

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.navigation.Router
import com.example.navigation.Screen.Companion.KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val router: Router,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val colorValue = savedStateHandle.get<Int>(KEY) ?: error("")
}