package com.github.aleksandrgrebenkin.materialdesign.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}