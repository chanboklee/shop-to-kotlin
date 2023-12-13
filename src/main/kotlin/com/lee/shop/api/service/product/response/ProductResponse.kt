package com.lee.shop.api.service.product.response

import com.lee.shop.domain.product.Product

class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
) {

    companion object {
        fun of(product: Product): ProductResponse{
            return ProductResponse(
                id = product.id!!,
                name = product.name,
                price = product.price,
                stockQuantity = product.stockQuantity
            )
        }
    }
}