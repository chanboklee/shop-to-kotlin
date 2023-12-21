package com.lee.shop.domain.order

enum class OrderStatus(
    val text: String,
) {
    ORDER("주문"),
    CANCEL("주문취소");
}