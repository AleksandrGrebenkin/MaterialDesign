package com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstronomyPictureOfTheDay(
    @Expose val date: String,
    @Expose val mediaType: String,
    @Expose val explanation: String = "",
    @Expose val title: String = "",
    @Expose val url: String?,
    @Expose val thumbnailUrl: String?
) : Parcelable