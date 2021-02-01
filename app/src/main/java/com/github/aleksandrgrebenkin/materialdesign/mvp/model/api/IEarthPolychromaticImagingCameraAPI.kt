package com.github.aleksandrgrebenkin.materialdesign.mvp.model.api

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.EarthPolychromaticImagingCamera
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IEarthPolychromaticImagingCameraAPI {

    @GET("api/natural/images")
    fun getEarthImages(
        @Query("api_key") apiKey: String,
    ): Single<List<EarthPolychromaticImagingCamera>>
}