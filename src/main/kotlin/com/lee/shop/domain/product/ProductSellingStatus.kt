package com.lee.shop.domain.product

enum class ProductSellingStatus(
    val text: String,
) {
    SELLING("판매중"),
    STOP_SELLING("판매중지"),
    HOLD("판매보류")
}