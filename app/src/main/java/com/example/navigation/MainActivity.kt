package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.navigation.color.Colored
import com.example.navigation.fisrt.GreetingFirst
import com.example.navigation.second.GreetingSecond
import com.example.navigation.third.GreetingThird
import com.example.navigation.ui.theme.NavigationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rootNavController = rememberNavController()
            val viewModel = hiltViewModel<MainViewModel>()

            val lifecycleOwner = LocalLifecycleOwner.current
            LaunchedEffect(Unit) {
                viewModel.router
                    .rootFlow()
                    .flowWithLifecycle(lifecycleOwner.lifecycle)
                    .onEach(rootNavController::navigate)
                    .launchIn(lifecycleOwner.lifecycleScope)
            }
            NavigationTheme {
                NavHost(
                    navController = rootNavController,
                    startDestination = Screen.First.route,
                ) {
                    composable(Screen.First.route) { GreetingFirst() }
                    navigation(
                        startDestination = Screen.SecondInitial.route,
                        route = Screen.Second.route
                    ) {
                        composable(Screen.SecondInitial.route) { GreetingSecond() }
                        composable(
                            route = Screen.Color.route,
                            arguments = Screen.Color.navArgs
                        ) {
                            Colored()
                        }
                    }
                    composable(Screen.Third.route) { GreetingThird() }
                }
            }
        }
    }
}

@Composable
inline fun <reified T> Flow<T>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
            .onEach(action)
            .launchIn(lifecycleOwner.lifecycleScope)
    }
}