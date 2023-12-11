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
}