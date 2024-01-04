package com.lee.shop.domain.product

import com.lee.shop.domain.product.ProductSellingStatus.SELLING
import com.lee.shop.domain.product.ProductType.BOTTOM
import com.lee.shop.domain.product.ProductType.TOP
import org.assertj.core.api.Assertions.assertThat
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
}