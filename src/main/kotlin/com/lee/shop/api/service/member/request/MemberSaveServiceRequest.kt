package com.lee.shop.api.service.member.request

import com.lee.shop.domain.member.Member

class MemberSaveServiceRequest(
    var name: String,
    var email: String,
    var password: String
) {
    fun toEntity(): Member {
        return Member(
            null,
            name,
            email,
            password
        )
    }

    companion object {
        fun fixture(
            name: String = "이찬복",
            email: String = "chanboklee@naver.com",
            password: String = "1234",
        ): MemberSaveServiceRequest{
            return MemberSaveServiceRequest(
                name = name,
                email = email,
                password = password,
            )
        }
    }
}