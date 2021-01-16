package com.github.aleksandrgrebenkin.materialdesign.ui.image

import android.widget.ImageView
import coil.api.load
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.image.IImageLoader

class CoilImageLoader: IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        container.load(url) {
//            error()
//            placeholder()
        }
    }
}