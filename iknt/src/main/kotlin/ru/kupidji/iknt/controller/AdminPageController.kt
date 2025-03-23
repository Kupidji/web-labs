package ru.kupidji.iknt.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class AdminPagesController {

    @GetMapping("/admin")
    fun redirectToNews(): String {
        return "redirect:/admin/news"
    }

}