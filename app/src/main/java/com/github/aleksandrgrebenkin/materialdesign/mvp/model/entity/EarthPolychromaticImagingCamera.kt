package com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class EarthPolychromaticImagingCamera(
    @Expose val image: String,
    @Expose val date: String,
) : Parcelable