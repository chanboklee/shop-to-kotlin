package com.lee.shop.api.service.product.request

import com.lee.shop.domain.product.Product
import com.lee.shop.domain.product.ProductSellingStatus
import com.lee.shop.domain.product.ProductSellingStatus.SELLING
import com.lee.shop.domain.product.ProductType
import com.lee.shop.domain.product.ProductType.TOP

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

    companion object {
        fun fixture(
            name: String = "맨투맨",
            price: Int = 12000,
            stockQuantity: Int = 20,
            productType: ProductType = TOP,
            productSellingStatus: ProductSellingStatus = SELLING,
        ): ProductSaveServiceRequest{
            return ProductSaveServiceRequest(
                productNumber = null,
                name = name,
                price = price,
                stockQuantity = stockQuantity,
                productType = productType,
                productSellingStatus = productSellingStatus,
            )
        }
    }
}