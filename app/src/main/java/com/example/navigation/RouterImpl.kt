package com.example.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject

class RouterImpl @Inject constructor() : Router {
    private lateinit var channel: Channel<String>

    override suspend fun navigateInRoot(destination: String) {
        channel.send(destination)
    }

    override fun rootFlow(): Flow<String> {
        channel = Channel()
        return channel.consumeAsFlow()
    }
}