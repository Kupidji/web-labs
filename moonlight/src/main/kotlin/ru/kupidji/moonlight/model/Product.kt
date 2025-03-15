package ru.kupidji.moonlight.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor


@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") val id: Long = 0,
    @Column(name = "name") val name: String,
    @Column(name = "price") val price: String,
    @Column(name = "image_url") val imageUrl: String,
    @Column(name = "description") val description: String,
)