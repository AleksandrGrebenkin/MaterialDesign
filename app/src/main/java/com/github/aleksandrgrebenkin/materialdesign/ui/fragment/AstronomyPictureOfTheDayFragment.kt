package com.github.aleksandrgrebenkin.materialdesign.ui.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.FragmentAstronomyPictureOfTheDayStartBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.AstronomyPictureOfTheDayPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.AstronomyPictureOfTheDayView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.regex.Pattern
import javax.inject.Inject


class AstronomyPictureOfTheDayFragment : MvpAppCompatFragment(),
    AstronomyPictureOfTheDayView,
    BackButtonListener {

    companion object {
        fun newInstance() = AstronomyPictureOfTheDayFragment()
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private var _binding: FragmentAstronomyPictureOfTheDayStartBinding? = null
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
        _binding = FragmentAstronomyPictureOfTheDayStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.explanation.typeface =
            Typeface.createFromAsset(context?.assets, "fonts/HaeresletterRegular.otf")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_app_bar, menu)
    }

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
        val spannableText = SpannableString(text)
        var startIndex = 0
        var endIndex = 0
        val spannableFilter = listOf("sky", "night", "star", "stars")
        val endOfWordFilter = listOf(" ", ".", ",", "!", "?")
        while (true) {
            startIndex = spannableText.indexOfAny(spannableFilter, endIndex, true)
            if (startIndex < 0) break
            endIndex = spannableText.indexOfAny(endOfWordFilter, startIndex, true)
            spannableText.setSpan(
                ForegroundColorSpan(Color.RED),
                startIndex,
                endIndex,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
        binding.explanation.text = spannableText
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