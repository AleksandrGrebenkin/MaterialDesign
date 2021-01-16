package com.github.aleksandrgrebenkin.materialdesign.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun navigationHolder(): NavigatorHolder = cicerone.navigatorHolder
}