package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.retrofit

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IEarthPolychromaticImagingCameraAPI
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IEarthPolychromaticImagingCameraRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitEarthPolychromaticImagingCameraRepo(
    val api: IEarthPolychromaticImagingCameraAPI
) : IEarthPolychromaticImagingCameraRepo {

    override fun getEarthImages(
        apiKey: String
    ) = api.getEarthImages(apiKey)
        .subscribeOn(Schedulers.io())
}