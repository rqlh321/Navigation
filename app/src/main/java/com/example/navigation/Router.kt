package com.example.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow

interface Router {
    suspend fun navigateInRoot(rout: String)
    suspend fun navigate(destination: Destination)
    fun getDestinationFlow(): Flow<Destination>

    data class Destination(
        val layer: String,
        val rout: String,
        val options: NavOptionsBuilder.() -> Unit = {}
    )
}