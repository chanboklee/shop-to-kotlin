package com.lee.shop.api.controller.product

import com.lee.shop.api.controller.product.request.ProductSaveRequest
import com.lee.shop.api.service.product.ProductService
import com.lee.shop.api.service.product.response.ProductResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping("/products")
    fun saveProduct(@Valid @RequestBody productSaveRequest: ProductSaveRequest): ProductResponse{
        return productService.saveProduct(productSaveRequest.toServiceRequest())
    }
}