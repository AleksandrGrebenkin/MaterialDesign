package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.retrofit

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IAstronomyPictureOfTheDayAPI
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IAstronomyPictureOfTheDayRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitAstronomyPictureOfTheDayRepo(
    val api: IAstronomyPictureOfTheDayAPI
) : IAstronomyPictureOfTheDayRepo {

    override fun getPictureOfTheDay(
        apiKey: String,
        thumbs: String
    ) = api.getPictureOfTheDay(apiKey, thumbs)
        .subscribeOn(Schedulers.io())

    override fun getPictureOfTheDay(
        apiKey: String,
        thumbs: String,
        date: String
    ) = api.getPictureOfTheDay(apiKey, thumbs, date)
        .subscribeOn(Schedulers.io())
}