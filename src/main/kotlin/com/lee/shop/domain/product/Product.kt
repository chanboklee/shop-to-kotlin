package com.lee.shop.domain.product

import com.lee.shop.domain.product.ProductSellingStatus.SELLING
import com.lee.shop.domain.product.ProductType.TOP
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

    companion object {
        fun fixture(
            productNumber: String = "001",
            name: String = "맨투맨",
            price: Int = 12000,
            stockQuantity: Int = 20,
            productType: ProductType = TOP,
            productSellingStatus: ProductSellingStatus = SELLING,
        ): Product{
            return Product(
                productNumber = productNumber,
                name = name,
                price = price,
                stockQuantity = stockQuantity,
                productType = productType,
                productSellingStatus = productSellingStatus,
            )
        }
    }
}