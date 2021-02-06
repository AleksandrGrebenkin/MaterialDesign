package com.github.aleksandrgrebenkin.materialdesign.ui.activity

import android.os.Bundle
import com.github.aleksandrgrebenkin.materialdesign.R
import com.github.aleksandrgrebenkin.materialdesign.databinding.ActivityMainBinding
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.IPreferencesManager
import com.github.aleksandrgrebenkin.materialdesign.mvp.model.preferences.ThemePreference
import com.github.aleksandrgrebenkin.materialdesign.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.materialdesign.mvp.view.MainView
import com.github.aleksandrgrebenkin.materialdesign.ui.App
import com.github.aleksandrgrebenkin.materialdesign.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var preferenceManager: IPreferencesManager

    private val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            binding.container.id
        )
    }

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.appComponent.inject(this)
        initTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        presenter.onViewCreated()
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun init() {
        setBottomNavigation()
    }

    private fun initTheme() {
        preferenceManager.getTheme()?.let {
            when (it) {
                ThemePreference.LIGHT -> setTheme(R.style.Light)
                ThemePreference.NIGHT -> setTheme(R.style.Night)
                ThemePreference.MARS -> setTheme(R.style.Mars)
                ThemePreference.ANDROID -> setTheme(R.style.Android)
            }
        } ?: setTheme(R.style.Android)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_navigation_astronomy_picture_of_the_day -> {
                    presenter.navigationAPODClicked()
                    true
                }
                R.id.bottom_navigation_planet_earth -> {
                    presenter.navigationPlanetEarthClicked()
                    true
                }
                R.id.bottom_navigation_settings -> {
                    presenter.navigationSettingsClicked()
                    true
                }
                R.id.bottom_navigation_notes -> {
                    presenter.navigationNotesClicked()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            return@setOnNavigationItemReselectedListener
        }
    }
}