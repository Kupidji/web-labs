package ru.kupidji.iknt.controller.admin

import jakarta.validation.Valid
import lombok.AllArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import ru.kupidji.iknt.model.ErrorModel
import ru.kupidji.iknt.model.Teacher
import ru.kupidji.iknt.model.TeacherDTO
import ru.kupidji.iknt.repository.TeacherRepository
import ru.kupidji.iknt.util.FragmentInjector.injectAdminFragment
import ru.kupidji.iknt.util.FragmentInjector.injectErrorFragment
import java.util.*


@Controller
@AllArgsConstructor
class AdminTeachersController(
    private val teacherRepository: TeacherRepository
) {

    @GetMapping("/admin/teachers")
    fun renderTeachersPage(model: Model): String {
        val teachers: MutableList<Teacher?> = teacherRepository.findAll(TeacherRepository.DEFAULT_SORT)
        model.addAttribute("teachers", teachers)
        injectAdminFragment(model, "teachers", "ИКНТ - Преподаватели")
        return "admin_page"
    }

    @GetMapping("/admin/teachers/create")
    fun createTeacherPage(model: Model): String {
        model.addAttribute("teacher", Teacher())
        model.addAttribute("formTitle", "Добавление преподавателя")
        model.addAttribute("formButton", "Добавить")
        injectAdminFragment(model, "crud/teacher", "ИКНТ - Добавление преподавателя")
        return "admin_page"
    }

    @PostMapping("/admin/teachers/create")
    fun createTeacher(dto: @Valid TeacherDTO): String {
        teacherRepository.save(Teacher(dto))
        return "redirect:/admin/teachers"
    }

    @GetMapping("/admin/teachers/{teacherId}/edit")
    fun editTeacherPage(@PathVariable teacherId: Long, model: Model): String {
        teacherRepository.findById(teacherId).ifPresentOrElse(
            { teacher ->
                model.addAttribute("teacher", teacher)
                model.addAttribute("formTitle", "Редактирование преподавателя")
                model.addAttribute("formButton", "Сохранить")
                injectAdminFragment(model, "crud/teacher", "ИКНТ - Редактирование преподавателя")
            },
            { injectErrorFragment(model, ErrorModel.ERROR_TEACHER_NOT_FOUND) }
        )

        return "admin_page"
    }

    @PostMapping("/admin/teachers/{teacherId}/edit")
    fun editTeacher(@PathVariable teacherId: Long, dto: @Valid TeacherDTO, model: Model?): String {
        val teacher: Optional<Teacher?> = teacherRepository.findById(teacherId)
        if (teacher.isEmpty) {
            injectErrorFragment(model!!, ErrorModel.ERROR_TEACHER_NOT_FOUND)
            return "admin_page"
        }

        teacher.get().update(dto)
        teacherRepository.save(teacher.get())
        return "redirect:/admin/teachers"
    }

    @GetMapping("/admin/teachers/{teacherId}/delete")
    fun deleteTeacher(@PathVariable teacherId: Long): String {
        teacherRepository.deleteById(teacherId)
        return "redirect:/admin/teachers"
    }
}
