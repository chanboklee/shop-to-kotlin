package com.lee.shop.api.service.product.response

import com.lee.shop.domain.product.Product
import com.lee.shop.domain.product.ProductSellingStatus
import com.lee.shop.domain.product.ProductType

class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
    val productType: ProductType,
    val productSellingStatus: ProductSellingStatus,
) {

    companion object {
        fun of(product: Product): ProductResponse{
            return ProductResponse(
                id = product.id!!,
                name = product.name,
                price = product.price,
                stockQuantity = product.stockQuantity,
                productType = product.productType,
                productSellingStatus = product.productSellingStatus
            )
        }
    }
}