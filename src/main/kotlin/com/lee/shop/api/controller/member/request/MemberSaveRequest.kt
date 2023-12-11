package com.lee.shop.api.controller.member.request

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest
import jakarta.validation.constraints.NotBlank

class MemberSaveRequest(
    @field:NotBlank(message = "성명은 필수입니다.")
    val name: String,
    @field:NotBlank(message = "이메일은 필수입니다.")
    val email: String,
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    val password: String,
) {

    fun toServiceRequest(): MemberSaveServiceRequest{
        return MemberSaveServiceRequest(
            name,
            email,
            password
        )
    }
}