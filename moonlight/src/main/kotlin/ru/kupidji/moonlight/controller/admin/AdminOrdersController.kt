package ru.kupidji.moonlight.controller.admin

import lombok.AllArgsConstructor
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ru.kupidji.moonlight.model.Order
import ru.kupidji.moonlight.repository.OrderRepository
import ru.kupidji.moonlight.util.injectAdminFragment


@Controller
@AllArgsConstructor
class AdminOrdersController(
    private val orderRepository: OrderRepository
) {

    @GetMapping("/admin/orders")
    fun renderOrdersPage(model: Model): String {
        val orders: List<Order> = orderRepository.findAll(Sort.by(Sort.Order.desc("orderDate")))
        model.addAttribute("orders", orders)
        injectAdminFragment(model, "orders", "Moonlight - Заказы")
        return "admin_page"
    }

    @GetMapping("/admin/orders/{orderId}/delete")
    fun deleteOrder(@PathVariable orderId: Long): String {
        orderRepository.deleteById(orderId)
        return "redirect:/admin/orders"
    }
}