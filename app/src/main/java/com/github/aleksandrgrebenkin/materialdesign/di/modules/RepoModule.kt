package com.github.aleksandrgrebenkin.materialdesign.di.modules

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IAstronomyPictureOfTheDayAPI
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IAstronomyPictureOfTheDayRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.retrofit.RetrofitAstronomyPictureOfTheDayRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun astronomyPictureOfTheDayRepo(
        api: IAstronomyPictureOfTheDayAPI
    ): IAstronomyPictureOfTheDayRepo =
        RetrofitAstronomyPictureOfTheDayRepo(api)
}