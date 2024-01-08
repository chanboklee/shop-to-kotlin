package com.lee.shop.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class ProductTest @Autowired constructor(
    private val productRepository: ProductRepository,
){

    @AfterEach
    fun tearDown() {
        productRepository.deleteAllInBatch()
    }

    @DisplayName("주문을 취소하면 상품의 재고가 증가한다.")
    @Test
    fun addStock(){
        // given
        val product = Product.fixture("001", "맨투맨", 12000, 20, ProductType.TOP, ProductSellingStatus.SELLING)
        val savedProduct = productRepository.save(product)

        // when // then
        savedProduct?.addStock(10)
        val findProduct = productRepository.findByIdOrNull(savedProduct.id!!)
        assertThat(findProduct?.stockQuantity).isEqualTo(30)
    }

    @DisplayName("주문을 하면 주문수만큼 재고가 차감된다.")
    @Test
    fun removeStock(){
        // given
        val product = Product.fixture("001", "맨투맨", 12000, 20, ProductType.TOP, ProductSellingStatus.SELLING)
        val savedProduct = productRepository.save(product)

        // when // then
        savedProduct?.removeStock(10)
        val findProduct = productRepository.findByIdOrNull(savedProduct.id!!)
        assertThat(findProduct?.stockQuantity).isEqualTo(10)
    }
}