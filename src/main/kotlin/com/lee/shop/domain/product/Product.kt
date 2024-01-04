package com.lee.shop.domain.product

import com.lee.shop.domain.orderproduct.OrderProduct
import com.lee.shop.domain.product.ProductSellingStatus.SELLING
import com.lee.shop.domain.product.ProductType.TOP
import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long? = null,
    val productNumber: String,
    val name: String,
    val price: Int,
    var stockQuantity: Int,

    @Enumerated(EnumType.STRING)
    val productType: ProductType,

    @Enumerated(EnumType.STRING)
    val productSellingStatus: ProductSellingStatus,

    @OneToMany(mappedBy = "product")
    val orderProducts: MutableList<OrderProduct> = mutableListOf(),
) {

    fun addStock(quantity: Int){
        stockQuantity += quantity
    }

    fun removeStock(count: Int){
        val currentStock = stockQuantity - count
        if(currentStock < 0){
            throw IllegalArgumentException("재고가 부족합니다.")
        }
        stockQuantity -= count
    }

    companion object {
        fun fixture(
            productNumber: String = "001",
            name: String = "맨투맨",
            price: Int = 12000,
            stockQuantity: Int = 20,
            productType: ProductType = TOP,
            productSellingStatus: ProductSellingStatus = SELLING,
        ): Product{
            return Product(
                productNumber = productNumber,
                name = name,
                price = price,
                stockQuantity = stockQuantity,
                productType = productType,
                productSellingStatus = productSellingStatus,
            )
        }
    }
}