package com.lee.shop.api.service.product.request

import com.lee.shop.domain.product.Product
import com.lee.shop.domain.product.ProductSellingStatus
import com.lee.shop.domain.product.ProductType

class ProductSaveServiceRequest(
    val productNumber: String?,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
    val productType: ProductType,
    val productSellingStatus: ProductSellingStatus,
) {

    fun toEntity(productNumber: String): Product{
        return Product(
            null,
            productNumber,
            name,
            price,
            stockQuantity,
            productType,
            productSellingStatus
        )
    }
}