package com.example.navigation

import com.example.navigation.Screen.Companion.ROOT_LAYER
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject

class RouterImpl @Inject constructor() : Router {
    private lateinit var channel: Channel<Router.Destination>

    override suspend fun navigateInRoot(rout: String) {
        channel.send(Router.Destination(layer = ROOT_LAYER, rout = rout))
    }

    override suspend fun navigate(destination: Router.Destination) {
        channel.send(destination)
    }

    override fun getDestinationFlow(): Flow<Router.Destination> {
        channel = Channel()
        return channel.consumeAsFlow()
    }
}