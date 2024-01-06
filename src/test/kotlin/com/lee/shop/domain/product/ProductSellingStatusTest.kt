package com.lee.shop.domain.product

import com.lee.shop.domain.product.ProductSellingStatus.*
import com.lee.shop.domain.product.ProductType.TOP
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductSellingStatusTest @Autowired constructor(
    private val productRepository: ProductRepository,
) {

    @AfterEach
    fun tearDown() {
        productRepository.deleteAllInBatch()
    }

    @DisplayName("상품 판매상태가 판매중, 판매보류인 상품 리스트만 조회한다.")
    @Test
    fun forDisplay(){
        // given
        val product1 = Product.fixture("001", "맨투맨", 12000, 20, TOP, SELLING)
        val product2 = Product.fixture("002", "셔츠", 22000, 15, TOP, SELLING)
        val product3 = Product.fixture("003", "청바지", 32000, 20, ProductType.BOTTOM, STOP_SELLING)
        val product4 = Product.fixture("004", "면바지", 52000, 50, ProductType.BOTTOM, STOP_SELLING)
        val product5 = Product.fixture("005", "반바지", 24000, 5, ProductType.BOTTOM, HOLD)

        productRepository.saveAll(listOf(product1, product2, product3, product4, product5))

        // when
        val products = productRepository.findAllByProductSellingStatusIn(ProductSellingStatus.forDisplay())

        // then
        assertThat(products).hasSize(3)
            .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "맨투맨", 12000, 20, TOP, SELLING),
                tuple("002", "셔츠", 22000, 15, TOP, SELLING),
                tuple("005", "반바지", 24000, 5, ProductType.BOTTOM, HOLD)
        )
    }
}