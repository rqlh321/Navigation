package com.example.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    router: Router
) : ViewModel() {

    val controller = hashMapOf<String, NavController>()

    init {
        router.getDestinationFlow()
            .onEach { controller[it.layer]?.navigate(it.rout, it.options) }
            .launchIn(viewModelScope)
    }
}