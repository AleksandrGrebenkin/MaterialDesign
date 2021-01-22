package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter

import com.github.aleksandrgrebenkin.materialdesign.mvp.view.MainView
import com.github.aleksandrgrebenkin.materialdesign.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.AstronomyPictureOfTheDayScreen())
    }

    fun backPressed() {
        router.exit()
    }
}