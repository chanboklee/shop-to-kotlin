package com.lee.shop.api.service.member

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest
import com.lee.shop.api.service.member.response.MemberResponse
import com.lee.shop.domain.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun saveMember(memberSaveServiceRequest: MemberSaveServiceRequest): MemberResponse{
        val member = memberSaveServiceRequest.toEntity();
        val savedMember = memberRepository.save(member);
        return MemberResponse.of(savedMember)
    }
}