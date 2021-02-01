package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.EarthPolychromaticImagingCamera
import io.reactivex.rxjava3.core.Single

interface IEarthPolychromaticImagingCameraRepo {

    fun getEarthImages(apiKey: String) : Single<List<EarthPolychromaticImagingCamera>>
}