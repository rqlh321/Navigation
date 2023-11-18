package com.example.navigation.di

import com.example.navigation.Router
import com.example.navigation.RouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainModule {

    @Binds
    @Singleton
    fun bindRouter(impl: RouterImpl): Router
}