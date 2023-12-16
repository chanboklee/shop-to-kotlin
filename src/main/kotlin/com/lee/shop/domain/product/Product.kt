package com.lee.shop.domain.product

import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long? = null,
    val productNumber: String,
    val name: String,
    val price: Int,
    val stockQuantity: Int,

    @Enumerated(EnumType.STRING)
    val productType: ProductType,

    @Enumerated(EnumType.STRING)
    val productSellingStatus: ProductSellingStatus,
) {
}