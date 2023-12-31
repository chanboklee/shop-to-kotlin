package com.lee.shop.domain.order

import com.lee.shop.domain.delivery.Delivery
import com.lee.shop.domain.delivery.DeliveryStatus
import com.lee.shop.domain.orderproduct.OrderProduct
import jakarta.persistence.*

@Table(name = "orders")
@Entity
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    @OneToMany(mappedBy = "order")
    val orderProducts: MutableList<OrderProduct> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    val delivery: Delivery,
) {

    fun cancel(){
        if(delivery.deliveryStatus == DeliveryStatus.COMP){
            throw IllegalArgumentException("배송완료된 상품은 취소가 불가능합니다.")
        }
        orderStatus = OrderStatus.CANCEL
        for(orderProduct in orderProducts){
            orderProduct.cancel()
        }
    }

    companion object {
        fun createOrder(
            orderProducts: MutableList<OrderProduct>,
            delivery: Delivery,
        ): Order{
            return Order(
                orderStatus = OrderStatus.ORDER,
                orderProducts = orderProducts,
                delivery = delivery,
            )
        }
    }
}