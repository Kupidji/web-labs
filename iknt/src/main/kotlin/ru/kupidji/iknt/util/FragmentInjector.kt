package ru.kupidji.iknt.util

import lombok.AccessLevel
import lombok.NoArgsConstructor
import org.springframework.ui.Model
import ru.kupidji.iknt.model.ErrorModel


@NoArgsConstructor(access = AccessLevel.PRIVATE)
object FragmentInjector {
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
        injectFragment(model, "error", "ИКНТ - Ошибка")
        errorModel.injectInto(model)
    }
}