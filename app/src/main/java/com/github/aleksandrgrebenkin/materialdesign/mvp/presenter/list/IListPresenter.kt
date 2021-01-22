package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.list

import com.github.aleksandrgrebenkin.materialdesign.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}