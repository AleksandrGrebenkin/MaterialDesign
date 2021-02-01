package com.github.aleksandrgrebenkin.materialdesign.di.modules

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IEarthPolychromaticImagingCameraAPI
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class EPICModule {

    @Singleton
    @Named("EPIC")
    @Provides
    fun epicUrl() = "https://api.nasa.gov/EPIC/"

    @Singleton
    @Provides
    fun epicApi(
        @Named("EPIC") url: String,
        @Named("EPIC") gson: Gson,
        @Named("EPIC") client: OkHttpClient
    ): IEarthPolychromaticImagingCameraAPI =
        Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(IEarthPolychromaticImagingCameraAPI::class.java)

    @Singleton
    @Named("EPIC")
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Named("EPIC")
    @Provides
    fun okHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

}