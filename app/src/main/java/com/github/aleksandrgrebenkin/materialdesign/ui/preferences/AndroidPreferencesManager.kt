package com.github.aleksandrgrebenkin.materialdesign.ui.preferences

import android.content.Context
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.IPreferencesManager
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.ThemePreference
import com.github.aleksandrgrebenkin.materialdesign.ui.App

class AndroidPreferencesManager(val app: App) : IPreferencesManager {


    private val nameSharedPreferences = "PREFERENCES"
    private val themePreference = "THEME"
    private val sharedPreferences =
        app.getSharedPreferences(nameSharedPreferences, Context.MODE_PRIVATE)


    override fun getTheme(): ThemePreference? {
        val themeString = sharedPreferences.getString(themePreference, null)
        return themeString?.let { ThemePreference.valueOf(it) } ?: null
    }

    override fun setTheme(theme: ThemePreference) {
        sharedPreferences.edit()
            .putString(themePreference, theme.themeString)
            .apply()
    }
}