package com.github.aleksandrgrebenkin.materialdesign.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.FragmentSettingsBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.SettingsPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.SettingsView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import com.google.android.material.chip.Chip
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SettingsFragment : MvpAppCompatFragment(),
    SettingsView,
    BackButtonListener {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private val presenter by moxyPresenter {

        SettingsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.themeSettingsChipGroup.setOnCheckedChangeListener { group, checkedId ->
            group.findViewById<Chip>(checkedId)?.let {
                when (it.id) {
                    R.id.theme_light_chip -> presenter.themeLightChipClicked()
                    R.id.theme_night_chip -> presenter.themeNightChipClicked()
                    R.id.theme_mars_chip -> presenter.themeMarsChipClicked()
                    R.id.theme_android_chip -> presenter.themeAndroidChipClicked()
                }
            }
        }
    }

    override fun setThemeChipLight() {
        binding.themeLightChip.isSelected = true
    }

    override fun setThemeChipNight() {
        binding.themeNightChip.isSelected = true
    }

    override fun setThemeChipMars() {
        binding.themeMarsChip.isSelected = true
    }

    override fun setThemeChipAndroid() {
        binding.themeAndroidChip.isSelected = true
    }

    override fun setLightTheme() {
        setTheme(R.style.Light)
    }

    override fun setNightTheme() {
        setTheme(R.style.Night)
    }

    override fun setMarsTheme() {
        setTheme(R.style.Mars)
    }

    override fun setAndroidTheme() {
        setTheme(R.style.Android)
    }

    override fun backPressed() = presenter.backPressed()

    private fun showToast() {
        Toast.makeText(context, "pressed", Toast.LENGTH_SHORT).show()
    }

    private fun setTheme(resId: Int) {
        val activity = (activity as MvpAppCompatActivity)
        activity.setTheme(resId)
        activity.recreate()
    }
}