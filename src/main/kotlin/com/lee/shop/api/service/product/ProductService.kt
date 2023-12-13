package com.lee.shop.api.service.product

import com.lee.shop.api.service.product.request.ProductSaveServiceRequest
import com.lee.shop.api.service.product.response.ProductResponse
import com.lee.shop.domain.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    @Transactional
    fun saveProduct(productSaveServiceRequest: ProductSaveServiceRequest): ProductResponse{
        val product = productSaveServiceRequest.toEntity()
        val savedProduct = productRepository.save(product)
        return ProductResponse.of(savedProduct)
    }
}