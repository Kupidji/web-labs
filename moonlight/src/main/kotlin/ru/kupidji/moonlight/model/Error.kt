package ru.kupidji.moonlight.model

import org.springframework.ui.Model

@Suppress("unused")
@JvmRecord
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
        val ERROR_PRODUCT_NOT_FOUND: ErrorModel = ErrorModel("Указанный продукт не найден \uD83D\uDE15")
        val ERROR_ORDER_NOT_FOUND: ErrorModel = ErrorModel("Указанный заказ не найден \uD83D\uDE15")
    }
}