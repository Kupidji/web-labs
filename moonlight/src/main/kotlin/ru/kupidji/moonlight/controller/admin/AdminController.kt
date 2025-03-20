package ru.kupidji.moonlight.controller.admin

import jakarta.servlet.http.HttpServletResponse
import lombok.SneakyThrows
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminPagesController {

    @SneakyThrows
    @GetMapping("/admin")
    fun redirectToProducts(response: HttpServletResponse) {
        response.sendRedirect("/admin/products")
    }

}