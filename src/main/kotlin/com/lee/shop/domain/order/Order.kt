package com.lee.shop.domain.order

import jakarta.persistence.*

@Table(name = "orders")
@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,

    @OneToMany(mappedBy = "order")
    val orders: MutableList<Order> = mutableListOf(),
) {
}