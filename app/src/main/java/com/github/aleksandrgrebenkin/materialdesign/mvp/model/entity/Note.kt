package com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val title: String,
    val body: String,
) : Parcelable