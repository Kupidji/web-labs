package ru.kupidji.moonlight.util

import org.springframework.ui.Model
import ru.kupidji.moonlight.model.ErrorModel



fun injectAdminFragment(model: Model, page: String?, pageTitle: String?) {
    model.addAttribute("content", "fragments/admin/%s".formatted(page))
    model.addAttribute("page", page)
    model.addAttribute("pageTitle", pageTitle)
}

fun injectFragment(model: Model, page: String?, pageTitle: String?) {
    model.addAttribute("content", "fragments/%s".formatted(page))
    model.addAttribute("page", page)
    model.addAttribute("pageTitle", pageTitle)
}

fun injectErrorFragment(model: Model, errorModel: ErrorModel) {
    injectFragment(model, "error", "Moonlight - Ошибка")
    errorModel.injectInto(model)
}