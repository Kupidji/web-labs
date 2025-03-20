package ru.kupidji.moonlight.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") val id: Long = 0,
    @Column(name = "name") var name: String = "",
    @Column(name = "price") var price: String = "",
    @Column(name = "image_url") var imageUrl: String = "",
    @Column(name = "description") var description: String = "",
    @OneToMany(
        mappedBy = "product",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    var orders: MutableList<Order> = mutableListOf()
) {
    constructor(dto: ProductDTO): this(
        name = dto.name,
        price = dto.price,
        imageUrl = dto.imageUrl,
        description = dto.description,
    )

    fun update(dto: ProductDTO) {
        this.name = dto.name
        this.price = dto.price
        this.imageUrl = dto.imageUrl
        this.description = dto.description
    }
}