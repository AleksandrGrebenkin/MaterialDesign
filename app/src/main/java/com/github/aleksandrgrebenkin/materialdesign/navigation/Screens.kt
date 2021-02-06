package com.github.aleksandrgrebenkin.materialdesign.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.AstronomyPictureOfTheDayFragment
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.EarthFragment
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.NotesFragment
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.SettingsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class AstronomyPictureOfTheDayScreen() : SupportAppScreen() {
        override fun getFragment() = AstronomyPictureOfTheDayFragment.newInstance()
    }

    class WikiSearchScreen(private val url: String): SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
        }
    }

    class EarthScreen() : SupportAppScreen() {
        override fun getFragment() = EarthFragment.newInstance()
    }

    class SettingsScreen() : SupportAppScreen() {
        override fun getFragment() = SettingsFragment.newInstance()
    }

    class NotesScreen(): SupportAppScreen() {
        override fun getFragment() = NotesFragment.newInstance()
    }


}