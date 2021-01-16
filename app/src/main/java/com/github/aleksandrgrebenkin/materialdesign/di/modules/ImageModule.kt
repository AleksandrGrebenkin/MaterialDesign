package com.github.aleksandrgrebenkin.materialdesign.di.modules

import android.widget.ImageView
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.materialdesign.ui.image.CoilImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = CoilImageLoader()
}