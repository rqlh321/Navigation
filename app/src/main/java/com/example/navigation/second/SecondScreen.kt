package com.example.navigation.second

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigation.Screen
import com.example.navigation.second.a.AScreen
import com.example.navigation.second.b.BScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen() {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentDestination = navBackStackEntry?.destination
                listOf(Screen.A, Screen.B, Screen.C).forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(screen.route) },
                        selected = isSelected,
                        onClick = {
                            mainNavController.navigate(screen.route) {
                                val id = mainNavController.graph.findStartDestination().id
                                popUpTo(id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = mainNavController,
            startDestination = Screen.A.route
        ) {
            composable(Screen.A.route) { AScreen() }
            composable(Screen.B.route) { BScreen() }
            composable(Screen.C.route) { Text("C") }
        }
    }
}

