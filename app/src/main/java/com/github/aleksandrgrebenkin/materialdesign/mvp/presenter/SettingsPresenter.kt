package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.IPreferencesManager
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.ThemePreference
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.SettingsView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SettingsPresenter : MvpPresenter<SettingsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var preferenceManager: IPreferencesManager

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initThemePreference()
    }

    private fun initThemePreference() {
        when (preferenceManager.getTheme()) {
            ThemePreference.LIGHT -> viewState.setThemeChipLight()
            ThemePreference.NIGHT -> viewState.setThemeChipNight()
            ThemePreference.MARS -> viewState.setThemeChipMars()
            ThemePreference.ANDROID -> viewState.setThemeChipAndroid()
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun themeLightChipClicked() {
        val themePreference = ThemePreference.LIGHT
        if (checkThemeChangeNeed(themePreference)) {
            preferenceManager.setTheme(themePreference)
            viewState.setLightTheme()
        }
    }

    fun themeNightChipClicked() {
        val themePreference = ThemePreference.NIGHT
        if (checkThemeChangeNeed(themePreference)) {
            preferenceManager.setTheme(themePreference)
            viewState.setNightTheme()
        }
    }

    fun themeMarsChipClicked() {
        val themePreference = ThemePreference.MARS
        if (checkThemeChangeNeed(themePreference)) {
            preferenceManager.setTheme(themePreference)
            viewState.setMarsTheme()
        }
    }

    fun themeAndroidChipClicked() {
        val themePreference = ThemePreference.ANDROID
        if (checkThemeChangeNeed(themePreference)) {
            preferenceManager.setTheme(themePreference)
            viewState.setMarsTheme()
        }
    }

    private fun checkThemeChangeNeed(theme: ThemePreference): Boolean {
        return preferenceManager.getTheme() == null || preferenceManager.getTheme() != theme
    }
}