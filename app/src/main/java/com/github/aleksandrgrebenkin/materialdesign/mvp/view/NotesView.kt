package com.github.aleksandrgrebenkin.materialdesign.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface NotesView : MvpView {
    fun init()
    fun showError(text: String)
    fun updateList()

    fun addItem(position: Int)
    fun removeItem(position: Int)
    fun moveItem(fromPosition: Int, toPosition: Int)
    fun updateItem(position: Int)
}