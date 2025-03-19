package ru.kupidji.moonlight.controller

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import lombok.AllArgsConstructor
import lombok.SneakyThrows
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.kupidji.moonlight.model.ErrorModel
import ru.kupidji.moonlight.model.Order
import ru.kupidji.moonlight.model.OrderDTO
import ru.kupidji.moonlight.repository.OrderRepository
import ru.kupidji.moonlight.repository.ProductRepository


@Controller
@AllArgsConstructor
class OrdersController(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
) {

    @SneakyThrows
    @GetMapping("/order")
    fun renderPage(
        @RequestParam(value = "id", required = false) id: Long?,
        model: Model,
        response: HttpServletResponse,
    ): String? {
        if (id == null) {
            response.sendRedirect("/catalog")
            return null
        }

        productRepository.findById(id).ifPresentOrElse(
            { product ->
                model.addAttribute("content", "fragments/order")
                model.addAttribute("page", "order")
                model.addAttribute("pageTitle", "Moonlight - Оформление заказа")
                model.addAttribute("product", product)
            },
            {
                model.addAttribute("content", "fragments/error")
                model.addAttribute("page", "error")
                model.addAttribute("pageTitle", "Moonlight - Ошибка")
                ERROR_PRODUCT_NOT_FOUND.injectInto(model)
            }
        )

        return "page"
    }

    @PostMapping(value = ["/order"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun makeOrder(dto: @Valid OrderDTO, model: Model): String {
        productRepository.findById(dto.id).ifPresentOrElse(
            { product ->
                val order = Order(dto, product)
                orderRepository.save(order)

                model.addAttribute("content", "fragments/order_complete")
                model.addAttribute("page", "order_complete")
                model.addAttribute("pageTitle", "Moonlight - Заказ оформлен")
                model.addAttribute("order", order)
                model.addAttribute("product", product)
            },
            {
                model.addAttribute("content", "fragments/error")
                model.addAttribute("page", "error")
                model.addAttribute("pageTitle", "Moonlight - Ошибка")
                ERROR_PRODUCT_NOT_FOUND.injectInto(model)
            }
        )

        return "page"
    }

    companion object {
        private val ERROR_PRODUCT_NOT_FOUND = ErrorModel(
            "Ошибка!",
            "Указанный продукт не найден \uD83D\uDE15"
        )
    }
}