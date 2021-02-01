package com.github.aleksandrgrebenkin.materialdesign.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.materialdesign.databinding.ItemEarthBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.list.IEarthListPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.list.EarthItemView
import kotlinx.android.extensions.LayoutContainer
import javax.inject.Inject

class EarthRVAdapter (
    private val presenter: IEarthListPresenter
) : RecyclerView.Adapter<EarthRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemEarthBinding = ItemEarthBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemEarthBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemEarthBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, EarthItemView {

        override val containerView = binding.root
        override var pos = -1

        override fun setDateTime(text: String) {
            binding.datetime.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, binding.image)
        }
    }
}