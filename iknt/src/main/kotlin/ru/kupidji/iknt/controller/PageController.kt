package ru.kupidji.iknt.controller

import lombok.AllArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ru.kupidji.iknt.repository.NewsRepository
import ru.kupidji.iknt.repository.TeacherRepository
import ru.kupidji.iknt.util.FragmentInjector.injectFragment

@Controller
@AllArgsConstructor
class PageController(
    private val newsRepository: NewsRepository,
    private val teacherRepository: TeacherRepository,
) {

    @GetMapping("/")
    fun redirectToNews(): String {
        return "redirect:/news"
    }

    @GetMapping("/news")
    fun renderNewsPage(model: Model): String {
        model.addAttribute("newsList", newsRepository.findAll(NewsRepository.DEFAULT_SORT))
        injectFragment(model, "news", "ИКНТ - Новости")
        return "page"
    }

    @GetMapping("/about-us")
    fun renderAboutUsPage(model: Model): String {
        injectFragment(model, "about-us", "ИКНТ - О нас")
        return "page"
    }

    @GetMapping("/teachers")
    fun renderTeachersPage(model: Model): String {
        model.addAttribute("teachers", teacherRepository.findAll(TeacherRepository.DEFAULT_SORT))
        injectFragment(model, "teachers", "ИКНТ - Преподаватели")
        return "page"
    }

}
