package com.github.aleksandrgrebenkin.materialdesign.mvp.view.list

interface EarthItemView : IItemView {
    fun loadImage(url: String)
    fun setDateTime(text: String)
    fun setOnClickListenerExpand()
}