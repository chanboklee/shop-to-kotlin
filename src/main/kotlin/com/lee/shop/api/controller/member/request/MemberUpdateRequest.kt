package com.lee.shop.api.controller.member.request

import com.lee.shop.api.service.member.request.MemberUpdateServiceRequest

class MemberUpdateRequest(
    val name: String,
) {
    fun toServiceRequest(): MemberUpdateServiceRequest{
        return MemberUpdateServiceRequest(
            name = name,
        )
    }
}