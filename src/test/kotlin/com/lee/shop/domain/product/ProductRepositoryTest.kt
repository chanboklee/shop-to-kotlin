package com.lee.shop.domain.product

import com.lee.shop.domain.product.ProductSellingStatus.*
import com.lee.shop.domain.product.ProductType.BOTTOM
import com.lee.shop.domain.product.ProductType.TOP
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductRepositoryTest @Autowired constructor(
    private val productRepository: ProductRepository,
) {

    @AfterEach
    fun tearDown() {
        productRepository.deleteAllInBatch()
    }

    @DisplayName("ID를 내림차순으로 정렬한 후 제일 높은 한개를 가져온다.")
    @Test
    fun findTop1ByOrderByIdDesc(){
        // given
        val product1 = Product.fixture("001", "맨투맨", 12000, 20, TOP, SELLING)
        val product2 = Product.fixture("002", "청바지", 32000, 20, BOTTOM, SELLING)

        productRepository.saveAll(listOf(product1, product2))

        // when
        val findProduct = productRepository.findTop1ByOrderByIdDesc()

        // then
        assertThat(findProduct).extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactly("002", "청바지", 32000, 20, BOTTOM, SELLING)
    }

    @DisplayName("판매 상태에 해당하는 모든 상품을 조회한다.")
    @Test
    fun findAllByProductSellingStatusIn(){
        // given
        val product1 = Product.fixture("001", "맨투맨", 12000, 20, TOP, SELLING)
        val product2 = Product.fixture("002", "셔츠", 22000, 15, TOP, SELLING)
        val product3 = Product.fixture("003", "청바지", 32000, 20, BOTTOM, SELLING)
        val product4 = Product.fixture("004", "면바지", 52000, 50, BOTTOM, STOP_SELLING)
        val product5 = Product.fixture("005", "반바지", 24000, 5, BOTTOM, HOLD)

        productRepository.saveAll(listOf(product1, product2, product3, product4, product5))

        // when
        val products = productRepository.findAllByProductSellingStatusIn(listOf(SELLING, HOLD))

        // then
        assertThat(products).hasSize(4)
            .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "맨투맨", 12000, 20, TOP, SELLING),
                tuple("002", "셔츠", 22000, 15, TOP, SELLING),
                tuple("003", "청바지", 32000, 20, BOTTOM, SELLING),
                tuple("005", "반바지", 24000, 5, BOTTOM, HOLD)
            )
    }
}