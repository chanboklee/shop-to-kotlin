package com.lee.shop.api.controller.product.request

import com.lee.shop.api.service.product.request.ProductSaveServiceRequest
import com.lee.shop.domain.product.ProductSellingStatus
import com.lee.shop.domain.product.ProductType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

class ProductSaveRequest(
    @field:NotBlank(message = "상품명은 필수입니다.")
    val name: String,
    @field:Positive(message = "가격은 양수이어야 합니다.")
    val price: Int,
    @field:Positive(message = "재고량은 양수이어야 합니다.")
    val stockQuantity: Int,
    @field:NotNull(message = "상품타입은 필수입니다.")
    val productType: ProductType,
    @field:NotNull(message = "상품 판매상태는 필수입니다.")
    val productSellingStatus: ProductSellingStatus,
) {

    fun toServiceRequest(): ProductSaveServiceRequest{
        return ProductSaveServiceRequest(
            null,
            name,
            price,
            stockQuantity,
            productType,
            productSellingStatus
        )
    }
}