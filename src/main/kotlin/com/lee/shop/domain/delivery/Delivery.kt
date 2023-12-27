package com.lee.shop.domain.delivery

import com.lee.shop.domain.Address
import com.lee.shop.domain.member.Member
import jakarta.persistence.*

@Entity
class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    val id: Long? = null,

    @Embedded
    val address: Address,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,
) {

}