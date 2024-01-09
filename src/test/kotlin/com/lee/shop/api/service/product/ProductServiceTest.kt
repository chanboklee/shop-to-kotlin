package com.lee.shop.api.service.product

import com.lee.shop.api.service.product.request.ProductSaveServiceRequest
import com.lee.shop.domain.product.Product
import com.lee.shop.domain.product.ProductRepository
import com.lee.shop.domain.product.ProductSellingStatus
import com.lee.shop.domain.product.ProductSellingStatus.*
import com.lee.shop.domain.product.ProductSellingStatus.Companion.forDisplay
import com.lee.shop.domain.product.ProductType.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest @Autowired constructor(
    private val productService: ProductService,
    private val productRepository: ProductRepository,
){
    @AfterEach
    fun tearDown() {
        productRepository.deleteAllInBatch()
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    fun saveProductWhenProductsIsEmpty(){
        // given
        val productSaveServiceRequest = ProductSaveServiceRequest.fixture("맨투맨", 12000, 20, TOP, SELLING)

        // when
        val productResponse = productService.saveProduct(productSaveServiceRequest)

        // then
        assertThat(productResponse.id).isNotNull
        assertThat(productResponse).extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactly("001", "맨투맨", 12000, 20, TOP, SELLING)
    }

    @DisplayName("신규 상품을 등록하면 상품번호는 가장 최근에 등록한 상품의 상품번호에서 1 증가한 갑이다.")
    @Test
    fun saveProduct(){
        // given
        val product = Product.fixture("001", "청바지", 25000, 30, BOTTOM, SELLING)
        productRepository.save(product)

        // when
        val saveProduct = ProductSaveServiceRequest.fixture("맨투맨", 12000, 20, TOP, SELLING)
        val productResponse = productService.saveProduct(saveProduct)

        // then
        assertThat(productResponse).extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactly("002", "맨투맨", 12000, 20, TOP, SELLING)
    }

    @DisplayName("상품상태가 판매중, 판매보류인 상품 리스트만 조회한다.")
    @Test
    fun getSellingProducts(){
        // given
        val product1 = Product.fixture("001", "맨투맨", 25000, 100, TOP, SELLING)
        val product2 = Product.fixture("002", "귀걸이", 40000, 20, ACCESSORIES, STOP_SELLING)
        val product3 = Product.fixture("003", "패딩", 120000, 82, ACCESSORIES, HOLD)
        val product4 = Product.fixture("004", "청바지", 12000, 50, BOTTOM, SELLING)

        productRepository.saveAll(listOf(product1, product2, product3, product4))

        // when
        val products = productRepository.findAllByProductSellingStatusIn(forDisplay())

        // then
        assertThat(products).hasSize(3)
            .extracting("productNumber", "name", "price", "stockQuantity", "productType", "productSellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "맨투맨", 25000, 100, TOP, SELLING),
                tuple("003", "패딩", 120000, 82, ACCESSORIES, HOLD),
                tuple("004", "청바지", 12000, 50, BOTTOM, SELLING),
            )
    }
}