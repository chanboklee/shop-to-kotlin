package com.lee.shop.api.service.member

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest
import com.lee.shop.domain.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
){

    @AfterEach
    fun tearDown() {
        memberRepository.deleteAllInBatch()
    }

    @DisplayName("성명, 이메일, 비밀번호를 입력받아 신규 회원가입을 한다.")
    @Test
    fun saveMember(){
        // given
        val memberSaveServiceRequest = MemberSaveServiceRequest.fixture("이찬복", "chanboklee@naver.com", "1234")

        // when
        val memberResponse = memberService.saveMember(memberSaveServiceRequest)

        // then
        assertThat(memberResponse).extracting("name", "email", "password")
            .containsExactly("이찬복", "chanboklee@naver.com", "1234")
    }
}