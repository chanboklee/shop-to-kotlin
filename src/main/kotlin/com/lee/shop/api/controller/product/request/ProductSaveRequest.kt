package com.lee.shop.api.controller.product.request

import com.lee.shop.api.service.product.request.ProductSaveServiceRequest

class ProductSaveRequest(
    val name: String,
    val price: Int,
    val stockQuantity: Int,
) {

    fun toServiceRequest(): ProductSaveServiceRequest{
        return ProductSaveServiceRequest(
            name,
            price,
            stockQuantity
        )
    }
}