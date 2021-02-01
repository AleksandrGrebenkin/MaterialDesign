package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter

import com.github.aleksandrgrebenkin.materialdesign.BuildConfig
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.EarthPolychromaticImagingCamera
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IEarthPolychromaticImagingCameraRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.list.IEarthListPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.EarthView
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.list.EarthItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class EarthPresenter @Inject constructor(
    private val earthRepo: IEarthPolychromaticImagingCameraRepo,
    private val router: Router,
    private val uiScheduler: Scheduler,
    @Named("EPIC") private val apiUrl: String
) : MvpPresenter<EarthView>() {

    inner class EarthListPresenter : IEarthListPresenter {
        val earthImageList = mutableListOf<EarthPolychromaticImagingCamera>()

        override var itemClickListener: ((EarthItemView) -> Unit)? = null

        override fun bindView(view: EarthItemView) {
            val item = earthImageList[view.pos]

            view.setDateTime(getDateTimeString(item.date))
            view.loadImage(getImageUrl(item))
        }

        override fun getCount() = earthImageList.size


    }

    val earthImageListPresenter = EarthListPresenter()
    private var loadEarthImagesDisposable: Disposable? = null
    private val apiKey = BuildConfig.NASA_API_KEY

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        if (apiKey.isBlank()) {
            viewState.showError("You need API key")
        } else {
            loadEarthImagesDisposable = earthRepo.getEarthImages(apiKey)
                .observeOn(uiScheduler)
                .subscribe(
                    { earthImages ->
                        earthImageListPresenter.earthImageList.clear()
                        earthImageListPresenter.earthImageList.addAll(earthImages)
                        viewState.updateList()
                    },
                    { error ->
                        viewState.showError(error.message ?: error.stackTraceToString())
                    }
                )
        }
    }

    private fun getImageUrl(item: EarthPolychromaticImagingCamera): String {
        val sdfFromDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val sdfToDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return "${apiUrl}archive/natural/${sdfToDate.format(sdfFromDate.parse(item.date) ?: "1900-01-01 00:00:00")}" +
                "/jpg/${item.image}.jpg?api_key=${apiKey}"
    }

    private fun getDateTimeString(dateString: String): String {
        val sdfFromDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val sdfToDate = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
        return sdfToDate.format(sdfFromDate.parse(dateString) ?: "1900-01-01 00:00:00")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        loadEarthImagesDisposable?.dispose()
    }

    fun viewPagerSelectedPosition(position: Int) {
        viewState.addBottomDots(position, earthImageListPresenter.earthImageList.size)
    }

}