package com.github.aleksandrgrebenkin.materialdesign.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.materialdesign.databinding.FragmentNotesBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.NotesPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.NotesView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import com.github.aleksandrgrebenkin.materialdesign.ui.adapter.NoteItemTouchHelperCallback
import com.github.aleksandrgrebenkin.materialdesign.ui.adapter.NotesRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NotesFragment : MvpAppCompatFragment(), NotesView, BackButtonListener {
    companion object {
        fun newInstance() = NotesFragment()
    }

    @Inject
    lateinit var injectPresenter: Provider<NotesPresenter>

    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        injectPresenter.get()
    }

    private var adapter: NotesRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
        adapter = NotesRVAdapter(presenter.noteListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        binding.rvNotes.adapter = adapter

        binding.fabAdd.setOnClickListener {
            presenter.addNewItem()
        }

        ItemTouchHelper(NoteItemTouchHelperCallback(presenter))
            .attachToRecyclerView(binding.rvNotes)
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Log.e("MY_ERROR", text)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun addItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun removeItem(position: Int) {
        adapter?.notifyItemRemoved(position)
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) {
        adapter?.notifyItemMoved(fromPosition, toPosition)
    }

    override fun updateItem(position: Int) {
        adapter?.notifyItemChanged(position)
    }

    override fun backPressed() = presenter.backPressed()
}