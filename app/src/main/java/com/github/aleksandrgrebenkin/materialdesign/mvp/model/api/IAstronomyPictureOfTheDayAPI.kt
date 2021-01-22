package com.github.aleksandrgrebenkin.materialdesign.mvp.model.api

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.AstronomyPictureOfTheDay
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IAstronomyPictureOfTheDayAPI {

    @GET("apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("thumbs") thumbs: String
    ): Single<AstronomyPictureOfTheDay>

    @GET("apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("thumbs") thumbs: String,
        @Query("date") date: String
    ): Single<AstronomyPictureOfTheDay>
}