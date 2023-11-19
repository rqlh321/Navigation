package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {

    fun routeWithArgs(value: Any = Any()) = route.replace("{${KEY}}", value.toString())

    object A : Screen("a")
    object B : Screen("b")
    object C : Screen("c")

    object First : Screen("1")
    object Second : Screen("2")
    object Color : Screen(
        route = "color/{${KEY}}",
        navArgs = listOf(navArgument(KEY) { type = NavType.IntType })
    )

    companion object {
        const val KEY = "key"
        const val ROOT_LAYER = "root"
    }
}