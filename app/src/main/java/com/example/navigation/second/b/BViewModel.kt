package com.example.navigation.second.b

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Router
import com.example.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {
    fun toSecond() {
        viewModelScope.launch {
            router.navigateInRoot(Screen.Second.route)
        }
    }
}