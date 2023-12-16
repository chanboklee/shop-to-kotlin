package com.lee.shop.api.service.product

import com.lee.shop.api.service.product.request.ProductSaveServiceRequest
import com.lee.shop.api.service.product.response.ProductResponse
import com.lee.shop.domain.product.ProductRepository
import com.lee.shop.domain.product.ProductSellingStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    @Transactional
    fun saveProduct(productSaveServiceRequest: ProductSaveServiceRequest): ProductResponse {
        val productNumber = createProductNumber()
        val product = productSaveServiceRequest.toEntity(productNumber)
        val savedProduct = productRepository.save(product)
        return ProductResponse.of(savedProduct)
    }

    fun getSellingProducts(): List<ProductResponse>{
        return productRepository.findAllByProductSellingStatusIn(ProductSellingStatus.forDisplay())
            .map { product -> ProductResponse.of(product) }
    }

    private fun createProductNumber(): String {
        val product = productRepository.findTop1ByOrderByIdDesc() ?: return "001"
        val currentProductNumber = product.productNumber
        val nextProductNumber = currentProductNumber.toInt() + 1
        return "%03d".format(nextProductNumber)
    }
}