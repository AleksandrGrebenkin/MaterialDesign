package com.github.aleksandrgrebenkin.materialdesign.di.modules

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.IPreferencesManager
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.preferences.AndroidPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun preferencesManager(app: App): IPreferencesManager = AndroidPreferencesManager(app)
}