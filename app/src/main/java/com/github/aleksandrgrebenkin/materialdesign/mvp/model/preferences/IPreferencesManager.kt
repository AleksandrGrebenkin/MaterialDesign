package com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences

interface IPreferencesManager {
    fun getTheme(): ThemePreference?
    fun setTheme(theme: ThemePreference)
}