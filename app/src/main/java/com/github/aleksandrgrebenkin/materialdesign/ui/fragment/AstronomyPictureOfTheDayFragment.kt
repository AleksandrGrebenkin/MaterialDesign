package com.github.aleksandrgrebenkin.materialdesign.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.FragmentAstronomyPictureOfTheDayBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.AstronomyPictureOfTheDayPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.AstronomyPictureOfTheDayView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import com.github.aleksandrgrebenkin.materialdesign.ui.activity.MainActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class AstronomyPictureOfTheDayFragment : MvpAppCompatFragment(),
    AstronomyPictureOfTheDayView,
    BackButtonListener {

    companion object {
        fun newInstance() = AstronomyPictureOfTheDayFragment()
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var _binding: FragmentAstronomyPictureOfTheDayBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        AstronomyPictureOfTheDayPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstronomyPictureOfTheDayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_app_bar, menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.wiki -> presenter.onMenuWikiClicked()
//            R.id.settings -> presenter.onMenuSettingsClicked()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun init() {
        setWikiSearchListener()
    }

    override fun setImage(url: String) {
        imageLoader.loadInto(url, binding.picture)
    }

    override fun setTitle(text: String) {
        binding.title.text = text
    }

    override fun setExplanation(text: String) {
        binding.explanation.text = text
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Log.e("MY_ERROR", text)
    }

    override fun showWikiSearch() {
        binding.inputLayout.visibility = View.VISIBLE
    }

    override fun hideWikiSearch() {
        binding.inputLayout.visibility = View.GONE
    }

    override fun setWikiSearchTextEmpty() {
        binding.inputEditText.text = null
    }

    private fun setWikiSearchListener() {
        binding.inputLayout.setEndIconOnClickListener {
            val searchText = binding.inputEditText.text.toString()
            presenter.onWikiSearchClicked(searchText)
        }
    }

    override fun backPressed() = presenter.backPressed()
}