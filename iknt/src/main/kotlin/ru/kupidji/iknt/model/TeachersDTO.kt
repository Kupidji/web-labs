package ru.kupidji.iknt.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class TeachersDTO(
    @NotEmpty val firstName:  String,
    @NotEmpty val middleName: String,
    @NotEmpty val lastName: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull val birthDate: LocalDate,
    @NotEmpty val department: String,
    @NotNull var experience: Int,
    val photoUrl: String?
) {
    fun photoUrl(): String? = if (!photoUrl.isNullOrEmpty()) photoUrl else null
}