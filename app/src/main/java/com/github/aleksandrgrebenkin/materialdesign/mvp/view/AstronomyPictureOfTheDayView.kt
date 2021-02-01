package com.github.aleksandrgrebenkin.materialdesign.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface AstronomyPictureOfTheDayView : MvpView {
    fun init()
    fun setImage(url: String)
    fun setTitle(text: String)
    fun setExplanation(text: String)
    fun showError(text: String)
    fun showWikiSearch()
    fun hideWikiSearch()
    fun setWikiSearchTextEmpty()
}