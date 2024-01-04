package com.lee.shop.domain.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {

    fun findTop1ByOrderByIdDesc(): Product?
    fun findAllByProductSellingStatusIn(productSellingStatusList: List<ProductSellingStatus>): List<Product>
}