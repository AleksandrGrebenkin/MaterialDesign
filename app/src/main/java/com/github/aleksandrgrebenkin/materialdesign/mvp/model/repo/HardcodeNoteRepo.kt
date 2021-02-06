package com.github.aleksandrgrebenkin.materialdesign.mvp.model.repo

import com.github.aleksandrgrebenkin.materialdesign.mvp.model.entity.Note

class HardcodeNoteRepo : INoteRepo {
    override fun getNotes(): List<Note> {
        return listOf(
            Note("Курсы", "Записаться на курсы астронавтов"),
            Note("Программа", "Отобраться в астрономическую программу"),
            Note("Полет", "Долететь до Марса"),
            Note("Колонния", "Основать колоннию")
        )
    }
}