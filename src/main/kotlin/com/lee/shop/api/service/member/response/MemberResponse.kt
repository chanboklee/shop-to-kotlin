package com.lee.shop.api.service.member.response

import com.lee.shop.domain.member.Member

class MemberResponse(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
){
    companion object {
        fun of(member: Member): MemberResponse {
            return MemberResponse(
                id = member.id!!,
                name = member.name,
                email = member.email,
                password = member.password
            )
        }
    }
}