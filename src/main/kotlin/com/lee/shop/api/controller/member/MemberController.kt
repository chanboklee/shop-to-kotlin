package com.lee.shop.api.controller.member

import com.lee.shop.api.controller.member.request.MemberSaveRequest
import com.lee.shop.api.service.member.MemberService
import com.lee.shop.api.service.member.response.MemberResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class MemberController(
    private val memberService: MemberService,
) {

    @PostMapping("/members")
    fun saveMember(@Valid @RequestBody memberSaveRequest: MemberSaveRequest): MemberResponse{
        return memberService.saveMember(memberSaveRequest.toServiceRequest());
    }
}