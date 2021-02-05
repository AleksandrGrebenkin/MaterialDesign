package com.github.aleksandrgrebenkin.materialdesign.mvp.presenter

import android.view.View
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.Note
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo.INoteRepo
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.list.INoteListPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.NotesView
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.list.NoteItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NotesPresenter @Inject constructor(
    private val notesRepo: INoteRepo,
    private val router: Router,
) : MvpPresenter<NotesView>() {

    class NoteListPresenter : INoteListPresenter {
        val notes = mutableListOf<Note>()

        override var itemClickListener: ((NoteItemView) -> Unit)? = null

        override fun bindView(view: NoteItemView) {
            val note = notes[view.pos]
            view.setTitle(note.title)
            view.setBody(note.body)
        }

        override fun getCount() = notes.size
    }

    val noteListPresenter = NoteListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        noteListPresenter.itemClickListener = { noteItemView ->
            noteItemView.showBody()
        }
    }

    private fun loadData() {
        noteListPresenter.notes.clear()
        noteListPresenter.notes.addAll(notesRepo.getNotes())
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun addNewItem() {
        val newPosition = noteListPresenter.notes.size
        noteListPresenter.notes.add(newPosition, Note("New note", ""))
        viewState.addItem(newPosition)
    }

    fun removeItem(position: Int) {
        noteListPresenter.notes.removeAt(position)
        viewState.removeItem(position)
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        noteListPresenter.notes.let { list ->
            list.removeAt(fromPosition).apply {
                list.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
            }
        }
        viewState.moveItem(fromPosition, toPosition)
    }

}