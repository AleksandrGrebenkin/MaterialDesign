package com.github.aleksandrgrebenkin.materialdesign.di

import com.github.aleksandrgrebenkin.materialdesign.di.modules.*
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.AstronomyPictureOfTheDayPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.materialdesign.ui.activity.MainActivity
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.AstronomyPictureOfTheDayFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        ImageModule::class,
        NavigationModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(astronomyPictureOfTheDayFragment: AstronomyPictureOfTheDayFragment)

    fun inject(mainPresenter: MainPresenter)
    fun inject(astronomyPictureOfTheDayPresenter: AstronomyPictureOfTheDayPresenter)
}