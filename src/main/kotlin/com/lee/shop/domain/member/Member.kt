package com.lee.shop.domain.member

import jakarta.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long? = null,
    var name: String,
    val email: String,
    val password: String,
) {
    fun update(name: String) {
        this.name = name
    }

    companion object {
        fun fixture(
            id: Long? = null,
            name: String = "이찬복",
            email: String = "chanboklee@naver.com",
            password: String = "1234",
        ): Member{
            return Member(
                id = null,
                name = name,
                email = email,
                password = password,
            )
        }
    }

}