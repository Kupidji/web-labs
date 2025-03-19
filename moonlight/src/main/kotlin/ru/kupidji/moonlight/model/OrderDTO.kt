package ru.kupidji.moonlight.model

import jakarta.validation.constraints.*

data class OrderDTO(
    @NotNull @Min(1) val id: Long,
    @NotEmpty @Size(min = 2, max = 64) val customerName: String,
    @NotEmpty @Email @Size(min = 5, max = 64) val email: String,
    @NotEmpty @Size(min = 11, max = 11) val phoneNumber: String,
    @NotNull @Min(15) val size: Float,
    val insertion: String,
    @Size(max = 15) val engraving: String,
    @NotEmpty @Size(min = 4, max = 128) val address: String,
    @Size(max = 500) val comment: String,
    @NotNull @Min(1) @Max(10) val quantity: Int,
)
