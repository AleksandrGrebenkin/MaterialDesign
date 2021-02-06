package com.github.aleksandrgrebenkin.materialdesign.ui.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.FragmentEarthBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.EarthPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.EarthView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import com.github.aleksandrgrebenkin.materialdesign.ui.adapter.EarthRVAdapter
import com.github.aleksandrgrebenkin.materialdesign.ui.common.getColorResCompat
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class EarthFragment : MvpAppCompatFragment(), EarthView, BackButtonListener {
    companion object {
        fun newInstance() = EarthFragment()
    }

    @Inject
    lateinit var injectPresenter: Provider<EarthPresenter>

    private var _binding: FragmentEarthBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        injectPresenter.get()
    }

    private var adapter: EarthRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        adapter = EarthRVAdapter(presenter.earthImageListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        binding.viewpager2.adapter = adapter
        binding.viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.viewPagerSelectedPosition(position)
            }
        })
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Log.e("MY_ERROR", text)
    }

    override fun backPressed() = presenter.backPressed()

    override fun addBottomDots(position: Int, size: Int) {
        val dots = arrayOfNulls<TextView>(size)

        binding.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(context).apply {
                text =
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                        Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY)
                    else Html.fromHtml("&#8226;")
                textSize = 35F
                setTextColor(context.getColorResCompat(R.attr.colorPrimary))
                binding.layoutDots.addView(this)
            }
        }

        if (dots.isNotEmpty()) {
            context?.let {
                dots[position]?.setTextColor(it.getColorResCompat(R.attr.colorPrimaryVariant))
            }
        }
    }
}