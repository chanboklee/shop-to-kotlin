package com.lee.shop.api.controller.member

import com.lee.shop.api.controller.member.request.MemberSaveRequest
import com.lee.shop.api.controller.member.request.MemberUpdateRequest
import com.lee.shop.api.service.member.MemberService
import com.lee.shop.api.service.member.response.MemberResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @GetMapping("/members")
    fun getMembers(): List<MemberResponse>{
        return memberService.getMembers();
    }

    @GetMapping("/members/{memberId}")
    fun getMember(@PathVariable("memberId") memberId: Long): MemberResponse{
        return memberService.getMember(memberId)
    }

    @PutMapping("/members/{memberId}")
    fun updateMember(@PathVariable("memberId") memberId: Long, @Valid @RequestBody memberUpdateRequest: MemberUpdateRequest){
        memberService.updateMember(memberId, memberUpdateRequest.toServiceRequest())
    }

    @DeleteMapping("/members/{memberId}")
    fun deleteMember(@PathVariable("memberId") memberId: Long){
        memberService.deleteMember(memberId)
    }

}