package ru.kupidji.moonlight.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.URL

// Response DTO
data class ProductResponse(
    val id: Long,
    val name: String,
    val price: String,
    val imageUrl: String,
    val description: String,
)

fun Product.toResponse() = ProductResponse(
    id = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    description = description,
)

// Request DTO
data class CreateProductRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:Positive(message = "Price must be positive")
    val price: Double,

    @field:NotBlank(message = "Image URL is required")
    @field:URL(message = "Invalid URL format")
    val imageUrl: String,

    @field:NotBlank(message = "Description is required")
    val description: String,
)

fun CreateProductRequest.toEntity() = Product(
    name = name,
    price = price.toString().dropLast(2),
    imageUrl = imageUrl,
    description = description,
)