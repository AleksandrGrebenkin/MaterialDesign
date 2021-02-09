package com.github.aleksandrgrebenkin.materialdesign.ui.adapter

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.ItemNoteBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.list.INoteListPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.list.NoteItemView
import kotlinx.android.extensions.LayoutContainer

class NotesRVAdapter(
    val presenter: INoteListPresenter
) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemNoteBinding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemNoteBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, NoteItemView,
        ItemTouchHelperViewHolder {

        override val containerView = binding.root
        override var pos = -1

        override fun setTitle(text: String) {
            binding.title.text = text
        }

        override fun setBody(text: String) {
            binding.body.setText(text)
        }

        override fun showBody() {

        }

        override fun onItemSelected() {
            binding.cardContainer.setBackgroundResource(R.color.design_default_color_secondary_variant)
        }

        override fun onItemCleared() {
            binding.cardContainer.setBackgroundColor(containerView.cardBackgroundColor.defaultColor)
        }
    }
}
