package com.example.navigation.first

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Const.AUTH_STATE
import com.example.navigation.R
import com.example.navigation.Router
import com.example.navigation.Screen
import com.example.navigation.Screen.Companion.ROOT_LAYER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    context: Application,
    private val router: Router,
) : ViewModel() {

    private val prefs = context.getSharedPreferences(
        context.getString(R.string.prefs_name),
        Context.MODE_PRIVATE,
    )

    fun toMainScreen() {
        prefs.edit().putBoolean(AUTH_STATE, true).apply()

        viewModelScope.launch {
            router.navigate(
                Router.Destination(
                    layer = ROOT_LAYER,
                    rout = Screen.Second.route,
                    options = { popUpTo(Screen.First.route) { inclusive = true } }
                )
            )
        }
    }
}