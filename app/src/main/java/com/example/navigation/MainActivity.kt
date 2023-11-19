package com.example.navigation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.Const.AUTH_STATE
import com.example.navigation.Screen.Companion.ROOT_LAYER
import com.example.navigation.color.ColorScreen
import com.example.navigation.first.FirstScreen
import com.example.navigation.second.SecondScreen
import com.example.navigation.ui.theme.NavigationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isAuthorized = getSharedPreferences(
            getString(R.string.prefs_name),
            Context.MODE_PRIVATE,
        ).getBoolean(AUTH_STATE, false)

        setContent {
            val rootNavController = rememberNavController()
            val viewModel = hiltViewModel<MainViewModel>()

            DisposableEffect(Unit) {
                viewModel.controller[ROOT_LAYER] = rootNavController
                onDispose { viewModel.controller.remove(ROOT_LAYER) }
            }

            NavigationTheme {
                NavHost(
                    navController = rootNavController,
                    startDestination = if (isAuthorized)
                        Screen.Second.route else Screen.First.route,
                ) {
                    composable(Screen.First.route) { FirstScreen() }
                    composable(Screen.Second.route) { SecondScreen() }
                    composable(
                        route = Screen.Color.route,
                        arguments = Screen.Color.navArgs
                    ) { ColorScreen() }
                }
            }
        }
    }
}
