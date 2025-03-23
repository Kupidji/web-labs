package ru.kupidji.iknt.repository

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import ru.kupidji.iknt.model.Teacher

interface TeacherRepository : JpaRepository<Teacher?, Long?> {
    companion object {
        val DEFAULT_SORT: Sort = Sort.by(Sort.Direction.DESC, "birthDate")
    }
}