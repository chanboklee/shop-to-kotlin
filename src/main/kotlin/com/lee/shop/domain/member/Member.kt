package com.lee.shop.domain.member

import jakarta.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
) {

}