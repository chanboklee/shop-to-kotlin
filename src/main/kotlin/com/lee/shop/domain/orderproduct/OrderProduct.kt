package com.lee.shop.domain.orderproduct

import com.lee.shop.domain.order.Order
import com.lee.shop.domain.product.Product
import jakarta.persistence.*

@Entity
class OrderProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    val id: Long? = null,
    val price: Int,
    val quantity: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product,
) {
}