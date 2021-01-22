package com.github.aleksandrgrebenkin.materialdesign.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EarthView : MvpView {
    fun init()
    fun showError(text: String)
    fun updateList()
    fun addBottomDots(position: Int, size: Int)
}