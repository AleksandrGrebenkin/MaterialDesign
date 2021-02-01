package com.github.aleksandrgrebenkin.materialdesign.di.modules

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IAstronomyPictureOfTheDayAPI
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IEarthPolychromaticImagingCameraAPI
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IAstronomyPictureOfTheDayRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.IEarthPolychromaticImagingCameraRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.retrofit.RetrofitAstronomyPictureOfTheDayRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.retrofit.RetrofitEarthPolychromaticImagingCameraRepo
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

    @Singleton
    @Provides
    fun earthPolychromaticImagingCameraRepo(
        api: IEarthPolychromaticImagingCameraAPI
    ): IEarthPolychromaticImagingCameraRepo =
        RetrofitEarthPolychromaticImagingCameraRepo(api)
}