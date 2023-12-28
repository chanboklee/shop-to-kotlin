package com.lee.shop.domain.delivery

enum class DeliveryStatus(
    val text: String,
) {
    READY("배송준비"),
    COMP("배송완료"),
}