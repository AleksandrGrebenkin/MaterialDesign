package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.AstronomyPictureOfTheDay
import io.reactivex.rxjava3.core.Single

interface IAstronomyPictureOfTheDayRepo {

    fun getPictureOfTheDay(
        apiKey: String,
        thumbs: String
    ): Single<AstronomyPictureOfTheDay>

    fun getPictureOfTheDay(
        apiKey: String,
        thumbs: String,
        date: String
    ): Single<AstronomyPictureOfTheDay>
}