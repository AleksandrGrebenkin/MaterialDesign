package com.github.aleksandrgrebenkin.materialdesign.mvp.view.list

interface NoteItemView : IItemView {
    fun setTitle(text: String)
    fun setBody(text: String)
    fun showBody()
}