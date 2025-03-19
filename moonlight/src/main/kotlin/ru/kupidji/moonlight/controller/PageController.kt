package ru.kupidji.moonlight.controller

import lombok.AllArgsConstructor
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.kupidji.moonlight.model.Product
import ru.kupidji.moonlight.repository.ProductRepository
import ru.kupidji.moonlight.util.injectFragment


//@Controller
//@AllArgsConstructor
//class PagesController(
//    private val productRepository: ProductRepository,
//) {
//
//    @GetMapping("", "/")
//    fun renderIndexPage(model: Model): String {
//        injectFragment(model, "news", null)
//        return "page"
//    }
//
//    @GetMapping("/{page}")
//    fun renderPage(@PathVariable page: String, model: Model): String {
//        when (page) {
//            "news" -> injectFragment(model, page, null)
//            "catalog" -> injectFragment(model, page, "Moonlight - Каталог")
//            "about-us" -> injectFragment(model, page, "Moonlight - О нас")
//            else -> injectFragment(model, "error", "Moonlight - Ошибка")
//        }
//
//        if (page == "catalog") supplyCatalogItems(model)
//
//        return "page"
//    }
//
//    private fun supplyCatalogItems(model: Model) {
//        val products = productRepository.findAll()
//        model.addAttribute("products", products)
//    }
//
//
//}

@Controller
@AllArgsConstructor
class PagesController(
    private val productRepository: ProductRepository
) {

    @GetMapping("/")
    fun redirectToNews(model: Model): String {
        injectFragment(model, "news", null)
        return "page"
    }

    @GetMapping("/news")
    fun renderNewsPage(model: Model): String {
        injectFragment(model, "news", null)
        return "page"
    }

    @GetMapping("/catalog")
    fun renderCatalogPage(model: Model): String {
        val products: List<Product> = productRepository.findAll(Sort.by(Sort.Order.asc("id")))
        model.addAttribute("products", products)
        injectFragment(model, "catalog", "Moonlight - Каталог услуг")
        return "page"
    }

    @GetMapping("/about-us")
    fun renderAboutUsPage(model: Model): String {
        injectFragment(model, "about-us", "Moonlight - О нас")
        return "page"
    }
}