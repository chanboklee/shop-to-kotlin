package com.lee.shop.api.service.product.request

import com.lee.shop.domain.product.Product

class ProductSaveServiceRequest(
    val name: String,
    val price: Int,
    val stockQuantity: Int
) {

    fun toEntity(): Product{
        return Product(
            null,
            name,
            price,
            stockQuantity
        )
    }
}