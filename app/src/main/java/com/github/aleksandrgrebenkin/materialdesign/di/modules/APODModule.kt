package com.github.aleksandrgrebenkin.materialdesign.di.modules

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.api.IAstronomyPictureOfTheDayAPI
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class APODModule {

    @Singleton
    @Named("apodUrl")
    @Provides
    fun apodUrl() = "https://api.nasa.gov/planetary/"

    @Singleton
    @Provides
    fun apodApi(
        @Named("apodUrl") url: String,
        @Named("apodGSON") gson: Gson,
        @Named("apodClient") client: OkHttpClient
    ): IAstronomyPictureOfTheDayAPI =
        Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(IAstronomyPictureOfTheDayAPI::class.java)

    @Singleton
    @Named("apodGSON")
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Named("apodClient")
    @Provides
    fun apodOkHttpClient(apodInterceptor: APODInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apodInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    
    class APODInterceptor @Inject constructor(): Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}