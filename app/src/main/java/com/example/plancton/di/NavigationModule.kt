package com.example.plancton.di

import com.example.plancton.navigation.router.EntryRouter
import com.example.plancton.navigation.router.EntryRouterImpl
import com.example.plancton.navigation.router.EventRouter
import com.example.plancton.navigation.router.EventRouterImpl
import com.example.plancton.navigation.router.MainRouter
import com.example.plancton.navigation.router.MainRouterImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    companion object {
        private val cicerone: Cicerone<Router> = Cicerone.create()

        @Provides
        @AppScope
        fun provideRouter(): Router = cicerone.router

        @Provides
        @AppScope
        fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
    }

    @Binds
    @AppScope
    fun bindEntryRouter(impl: EntryRouterImpl): EntryRouter

    @Binds
    @AppScope
    fun bindMainRouter(impl: MainRouterImpl): MainRouter

    @Binds
    @AppScope
    fun bindEventRouter(impl: EventRouterImpl): EventRouter
}