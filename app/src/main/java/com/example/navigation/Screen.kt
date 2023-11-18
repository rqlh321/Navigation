package com.example.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {

    fun routeWithArgs(value: Any = Any()) = route.replace("{${KEY}}", value.toString())

    object First : Screen("1")
    object Second : Screen("2")
    object SecondInitial : Screen("2.1")
    object Color : Screen(
        route = "2.2/{${KEY}}",
        navArgs = listOf(navArgument(KEY) { type = NavType.IntType })
    )

    object Third : Screen("3")

    companion object {
        const val KEY = "key"
    }
}