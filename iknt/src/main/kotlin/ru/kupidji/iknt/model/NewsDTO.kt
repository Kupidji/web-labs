package ru.kupidji.iknt.model

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class NewsDTO(
    @NotEmpty val title: String,
    @NotEmpty val content: String,
    val coverUrl: String? = null,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull val publishedAt: LocalDate
) {
    fun coverUrl() = if (!this.coverUrl.isNullOrEmpty()) this.coverUrl else null
}