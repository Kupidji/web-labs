package ru.kupidji.moonlight.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime


@Getter
@NoArgsConstructor
@DynamicInsert
@Entity @Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @Column(name = "total_price", nullable = false)
    var totalPrice: Double,

    @Column(name = "customer_name", nullable = false)
    var customerName: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "phone", nullable = false)
    var phone: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "size", nullable = false)
    var size: Float,

    @Column(name = "insertion", nullable = true)
    var insertion: String? = null,

    @Column(name = "engraving", nullable = true)
    var engraving: String? = null,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,

    @Column(name = "comment", length = 500, nullable = true)
    var comment: String? = null,

    @Column(name = "order_date", nullable = false, columnDefinition = "timestamp")
    var orderDate: LocalDateTime = LocalDateTime.now()
) {
    constructor(dto: OrderDTO, product: Product) : this(
        product = product,
        totalPrice = calculateTotalPrice(product.price, dto.quantity),
        customerName = dto.customerName,
        email = dto.email,
        phone = dto.phoneNumber,
        address = dto.address,
        size = dto.size,
        insertion = dto.insertion,
        engraving = dto.engraving,
        quantity = dto.quantity,
        comment = dto.comment,
    )

    companion object {
        private fun calculateTotalPrice(priceStr: String, quantity: Int): Double {
            val cleanedPrice = priceStr.replace("[^\\d.,]".toRegex(), "")
                .replace(",", ".")
            return cleanedPrice.toDoubleOrNull()?.times(quantity) ?: 0.0
        }
    }
}