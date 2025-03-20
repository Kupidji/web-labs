package ru.kupidji.moonlight.model


data class ProductDTO(
    val id: Long = 0,
    val name: String,
    val price: String,
    val imageUrl: String,
    val description: String,
)