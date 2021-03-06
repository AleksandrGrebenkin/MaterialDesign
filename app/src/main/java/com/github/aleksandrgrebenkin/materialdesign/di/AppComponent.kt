package com.github.aleksandrgrebenkin.materialdesign.di

import com.github.aleksandrgrebenkin.materialdesign.di.modules.*
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.AstronomyPictureOfTheDayPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.SettingsPresenter
import com.github.aleksandrgrebenkin.materialdesign.ui.activity.MainActivity
import com.github.aleksandrgrebenkin.materialdesign.ui.adapter.EarthRVAdapter
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.AstronomyPictureOfTheDayFragment
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.EarthFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        APODModule::class,
        AppModule::class,
        EPICModule::class,
        ImageModule::class,
        NavigationModule::class,
        PreferencesModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(astronomyPictureOfTheDayFragment: AstronomyPictureOfTheDayFragment)
    fun inject(earthFragment: EarthFragment)
    fun inject(earthRVAdapter: EarthRVAdapter)

    fun inject(mainPresenter: MainPresenter)
    fun inject(astronomyPictureOfTheDayPresenter: AstronomyPictureOfTheDayPresenter)
    fun inject(settingsPresenter: SettingsPresenter)
}