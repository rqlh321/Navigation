package com.example.navigation

import kotlinx.coroutines.flow.Flow

interface Router {
    suspend fun navigateInRoot(destination: String)
    fun rootFlow(): Flow<String>
}