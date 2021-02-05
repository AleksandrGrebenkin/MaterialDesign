package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.Note

interface INoteRepo {
    fun getNotes(): List<Note>
}