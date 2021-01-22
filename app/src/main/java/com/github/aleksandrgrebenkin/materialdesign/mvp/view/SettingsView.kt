package com.github.aleksandrgrebenkin.materialdesign.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

@Skip
interface SettingsView : MvpView {

    fun setLightTheme()
    fun setNightTheme()
    fun setMarsTheme()
    fun setAndroidTheme()

    fun setThemeChipLight()
    fun setThemeChipNight()
    fun setThemeChipMars()
    fun setThemeChipAndroid()
}
