package ru.kupidji.iknt.model

import org.springframework.ui.Model

@Suppress("unused")
data class ErrorModel(
    val title: String,
    val message: String?
) {
    constructor(message: String?) : this("Ошибка!", message)

    fun injectInto(model: Model) {
        model.addAttribute("error", this)
    }

    companion object {
        val ERROR_NOT_FOUND: ErrorModel = ErrorModel("Запрашиваемая страница не найдена \uD83D\uDE15")
        val ERROR_BAD_REQUEST: ErrorModel = ErrorModel("Запрос отправлен неверно \uD83D\uDE15")
        val ERROR_NEWS_ITEM_NOT_FOUND: ErrorModel = ErrorModel("Указанная новость не найдена \uD83D\uDE15")
        val ERROR_TEACHER_NOT_FOUND: ErrorModel = ErrorModel("Указанный преподаватель не найден \uD83D\uDE15")
    }
}