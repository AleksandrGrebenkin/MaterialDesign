package com.github.aleksandrgrebenkin.materialdesign.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.aleksandrgrebenkin.materialdesign.ui.fragment.AstronomyPictureOfTheDayFragment
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


}