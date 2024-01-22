package com.lee.shop.api.service.member

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest
import com.lee.shop.api.service.member.request.MemberUpdateServiceRequest
import com.lee.shop.api.service.member.response.MemberResponse
import com.lee.shop.domain.member.MemberRepository
import com.lee.shop.util.fail
import org.springframework.data.repository.findByIdOrNull
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

    fun getMembers(): List<MemberResponse> {
        return memberRepository.findAll()
            .map { member -> MemberResponse.of(member) }
    }

    fun getMember(memberId: Long): MemberResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: fail("존재하지 않는 회원입니다.")
        return MemberResponse.of(member)
    }

    @Transactional
    fun updateMember(memberId: Long, memberUpdateServiceRequest: MemberUpdateServiceRequest) {
        val member = memberRepository.findByIdOrNull(memberId) ?: fail("존재하지 않는 회원입니다.")
        member.update(memberUpdateServiceRequest.name);
    }

    @Transactional
    fun deleteMember(memberId: Long) {
        val member = memberRepository.findByIdOrNull(memberId) ?: fail("존재하지 않는 회원입니다.")
        memberRepository.delete(member)
    }
}