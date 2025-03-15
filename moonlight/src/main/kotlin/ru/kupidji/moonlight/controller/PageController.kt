package ru.kupidji.moonlight.controller

import lombok.AllArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.kupidji.moonlight.repository.ProductRepository

@Controller
@AllArgsConstructor
class PagesController(
    private val productRepository: ProductRepository,
) {

    @GetMapping("", "/")
    fun renderIndexPage(model: Model): String {
        injectFragment(model, "news", null)
        return "page"
    }

    @GetMapping("/{page}")
    fun renderPage(@PathVariable page: String, model: Model): String {
        when (page) {
            "news" -> injectFragment(model, page, null)
            "catalog" -> injectFragment(model, page, "Moonlight - Каталог")
            "about-us" -> injectFragment(model, page, "Moonlight - О нас")
            else -> injectFragment(model, "error", "Moonlight - Ошибка")
        }

        if (page == "catalog") supplyCatalogItems(model)

        return "page"
    }

    private fun supplyCatalogItems(model: Model) {
        val products = productRepository.findAll()
        model.addAttribute("products", products)
    }

    private fun injectFragment(model: Model, page: String, pageTitle: String?) {
        model.addAttribute("content", "fragments/%s".formatted(page))
        model.addAttribute("page", page)
        model.addAttribute("pageTitle", pageTitle)
    }
}