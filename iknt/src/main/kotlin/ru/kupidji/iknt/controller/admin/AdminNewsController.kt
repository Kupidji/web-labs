package ru.kupidji.iknt.controller.admin

import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import ru.kupidji.iknt.model.ErrorModel
import ru.kupidji.iknt.model.News
import ru.kupidji.iknt.model.NewsDTO
import ru.kupidji.iknt.repository.NewsRepository
import ru.kupidji.iknt.util.FragmentInjector.injectAdminFragment
import ru.kupidji.iknt.util.FragmentInjector.injectErrorFragment
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Controller
@AllArgsConstructor
class AdminNewsController(
    private val newsRepository: NewsRepository,
) {

    companion object {
        val ISO_DATE_RU: DateTimeFormatter = DateTimeFormatter.ISO_DATE.localizedBy(Locale.forLanguageTag("ru-RU"))
    }

    @GetMapping("/admin/news")
    fun renderNewsPage(model: Model): String {
        val news: List<News?> = newsRepository.findAll(NewsRepository.DEFAULT_SORT)
        model.addAttribute("newsList", news)
        injectAdminFragment(model, "news", "ИКНТ - Новости")
        return "admin_page"
    }

    @GetMapping("/admin/news/create")
    fun createNewsItemPage(model: Model): String {
        model.addAttribute("newsItem", News())
        model.addAttribute("todayDate", LocalDate.now().format(ISO_DATE_RU))
        model.addAttribute("formTitle", "Добавление новости")
        model.addAttribute("formButton", "Добавить")
        injectAdminFragment(model, "crud/news_item", "ИКНТ - Добавление новости")
        return "admin_page"
    }

    @PostMapping("/admin/news/create")
    fun createNewsItem(dto: @Valid NewsDTO): String {
        newsRepository.save(News(dto))
        return "redirect:/admin/news"
    }

    @GetMapping("/admin/news/{newsItemId}/edit")
    fun editNewsItemPage(@PathVariable newsItemId: Long, model: Model): String {
        newsRepository.findById(newsItemId).ifPresentOrElse(
            { newsItem ->
                model.addAttribute("newsItem", newsItem)
                model.addAttribute("formTitle", "Редактирование новости")
                model.addAttribute("formButton", "Сохранить")
                injectAdminFragment(model, "crud/news_item", "ИКНТ - Редактирование новости")
            },
            { injectErrorFragment(model, ErrorModel.ERROR_NEWS_ITEM_NOT_FOUND) }
        )

        return "admin_page"
    }

    @PostMapping("/admin/news/{newsItemId}/edit")
    fun editNewsItem(@PathVariable newsItemId: Long, dto: @Valid NewsDTO, model: Model?): String {
        val newsItem: Optional<News?> = newsRepository.findById(newsItemId)
        if (newsItem.isEmpty) {
            injectErrorFragment(model!!, ErrorModel.ERROR_NEWS_ITEM_NOT_FOUND)
            return "admin_page"
        }

        newsItem.get().update(dto)
        newsRepository.save(newsItem.get())
        return "redirect:/admin/news"
    }

    @GetMapping("/admin/news/{newsItemId}/delete")
    fun deleteNewsItem(@PathVariable newsItemId: Long): String {
        newsRepository.deleteById(newsItemId)
        return "redirect:/admin/news"
    }
}
