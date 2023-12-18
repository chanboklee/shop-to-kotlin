package com.lee.shop.api.service.member

import com.lee.shop.api.service.member.request.MemberSaveServiceRequest
import com.lee.shop.domain.member.Member
import com.lee.shop.domain.member.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
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

    @DisplayName("가입된 회원의 전체 리스트를 조회한다.")
    @Test
    fun getMembers(){
        // given
        val member1 = Member.fixture(null, "이찬복", "chanboklee@naver.com", "1234")
        val member2 = Member.fixture(null, "홍길동", "hong@naver.com", "1234")
        val member3 = Member.fixture(null, "춘향이", "chun@naver.com", "1234")
        memberRepository.saveAll(listOf(member1, member2, member3))

        // when
        val memberResponseList = memberService.getMembers()

        // then
        assertThat(memberResponseList).hasSize(3)
        assertThat(memberResponseList).extracting("name", "email", "password")
            .containsExactlyInAnyOrder(
                tuple("이찬복", "chanboklee@naver.com", "1234"),
                tuple("홍길동", "hong@naver.com", "1234"),
                tuple("춘향이", "chun@naver.com", "1234"),
            )
    }
}