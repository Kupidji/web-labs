package ru.kupidji.moonlight.controller.admin

import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import ru.kupidji.moonlight.model.ErrorModel
import ru.kupidji.moonlight.model.Product
import ru.kupidji.moonlight.model.ProductDTO
import ru.kupidji.moonlight.repository.ProductRepository
import ru.kupidji.moonlight.util.injectAdminFragment
import ru.kupidji.moonlight.util.injectErrorFragment
import java.util.*


@Controller
@AllArgsConstructor
class AdminProductsController(
    private val productRepository: ProductRepository,
) {

    @GetMapping("/admin/products")
    fun renderProductsPage(model: Model): String {
        val products: List<Product> = productRepository.findAll(Sort.by(Sort.Order.asc("id")))
        model.addAttribute("products", products)
        injectAdminFragment(model, "products", "Moonlight - Услуги")
        return "admin_page"
    }

    @GetMapping("/admin/products/create")
    fun createProductPage(model: Model): String {
        model.addAttribute("product", Product())
        model.addAttribute("formTitle", "Создание нового товара")
        model.addAttribute("formButton", "Создать")
        injectAdminFragment(model, "crud/product", "Moonlight - Добавление услуги")
        return "admin_page"
    }

    @PostMapping("/admin/products/create")
    fun createProduct(dto: @Valid ProductDTO): String {
        productRepository.save(Product(dto))
        return "redirect:/admin/products"
    }

    @GetMapping("/admin/products/{productId}/edit")
    fun editProductPage(@PathVariable productId: Long, model: Model): String {
        productRepository.findById(productId).ifPresentOrElse(
            { product: Product? ->
                model.addAttribute("product", product)
                model.addAttribute("formTitle", "Редактирование услуги")
                model.addAttribute("formButton", "Сохранить")
                injectAdminFragment(model, "crud/product", "Moonlight - Добавление услуги")
            },
            { injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND) }
        )

        return "admin_page"
    }

    @PostMapping("/admin/products/{productId}/edit")
    fun editProduct(@PathVariable productId: Long, dto: @Valid ProductDTO, model: Model): String {
        val product: Optional<Product> = productRepository.findById(productId)
        if (product.isEmpty) {
            injectErrorFragment(model, ErrorModel.ERROR_PRODUCT_NOT_FOUND)
            return "admin_page"
        }

        product.get().update(dto)
        productRepository.save(product.get())
        return "redirect:/admin/products"
    }

    @GetMapping("/admin/products/{productId}/delete")
    fun deleteProduct(@PathVariable productId: Long): String {
        productRepository.deleteById(productId)
        return "redirect:/admin/products"
    }
}