package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter

import com.github.aleksandrgrebenkin.materialdesign.BuildConfig
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IAstronomyPictureOfTheDayRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.AstronomyPictureOfTheDayView
import com.github.aleksandrgrebenkin.materialdesign.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AstronomyPictureOfTheDayPresenter : MvpPresenter<AstronomyPictureOfTheDayView>() {

    @Inject
    lateinit var apodRepo: IAstronomyPictureOfTheDayRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiScheduler: Scheduler

    private var getAPODDisposable: Disposable? = null
    private var showInfo = false
    private var showWiki = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun loadData() {
        val apiKey = BuildConfig.NASA_API_KEY
        val thumbs = "true"
        if (apiKey.isBlank()) {
            viewState.showError("You need API key")
        } else {
            getAPODDisposable = apodRepo.getPictureOfTheDay(apiKey, thumbs)
                .observeOn(uiScheduler)
                .subscribe(
                    { apod ->

                        if (apod.mediaType == "video") {
                            apod.thumbnailUrl?.let { viewState.setImage(it) }
                        } else {
                            apod.url?.let { viewState.setImage(it) }
                        }

                        viewState.setTitle(apod.title)
                        viewState.setExplanation(apod.explanation)

                    },
                    { error ->
                        viewState.showError(error.message ?: error.stackTraceToString())
                    }
                )

        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        getAPODDisposable?.dispose()
    }

    fun onMenuInfoClicked() {
        if (!showInfo) {
            viewState.showInfoBottomSheet()
        } else {
            viewState.hideInfoBottomSheet()
        }
        showInfo = !showInfo
    }

    fun onMenuWikiClicked() {
        if (!showWiki) {
            viewState.showWikiSearch()
            viewState.setWikiSearchTextEmpty()
        } else {
            viewState.hideWikiSearch()
        }
        showWiki = !showWiki
    }

    fun onMenuSettingsClicked() {
//        TODO("Not yet implemented")
    }

    fun onWikiSearchClicked(searchText: String) {
        val wikiUrl = "https://en.wikipedia.org/wiki/"
        router.navigateTo(Screens.WikiSearchScreen(wikiUrl + searchText))
    }
}