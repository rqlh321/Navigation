package com.example.navigation.second

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Router
import com.example.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GreetingSecondViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {
    fun toFirstScreen() {
        viewModelScope.launch {
            router.navigateInRoot(Screen.First.route)
        }
    }

    fun toThirdScreen() {
        viewModelScope.launch {
            router.navigateInRoot(Screen.Third.route)
        }
    }

    fun toColorScreen() {
        viewModelScope.launch {
            router.navigateInRoot(Screen.Color.routeWithArgs(Color.CYAN))
        }
    }
}