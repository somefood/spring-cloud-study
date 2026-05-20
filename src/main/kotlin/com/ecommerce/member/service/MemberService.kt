package com.ecommerce.member.service

import com.ecommerce.member.api.JoinMemberRequest
import com.ecommerce.member.api.MemberResponse
import com.ecommerce.member.api.toEntity
import com.ecommerce.member.api.toResponse
import com.ecommerce.member.domain.MemberRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true) // 1. 기본적으로 읽기 전용 트랜잭션
class MemberService(
    private val memberRepository: MemberRepository
) {
    // (JPA Repository는 03.02에서 주입)

    @Transactional // 2. 쓰기 트랜잭션 추가
    fun join(request: JoinMemberRequest): MemberResponse {
        // 5. 이메일 중복 검사
        if (memberRepository.findByEmail(request.email) != null) {
            throw IllegalArgumentException("이미 가입된 이메일입니다: ${request.email}")
        }
        // 6. DTO -> Entity 변환 (확장 함수 사용)
        val newMember = request.toEntity()
        // 7. DB에 저장 (영속화)
        val savedMember = memberRepository.save(newMember)
        // 8. Entity -> DTO 변환 후 반환
        return savedMember.toResponse()
    }

    fun getMember(memberId: Long): MemberResponse {
        // 9. ID로 회원 조회 (코틀린 확장인 findByIdOrNull 사용)
        val member = memberRepository.findByIdOrNull(memberId)
            ?: throw EntityNotFoundException("해당 ID의 회원을 찾을 수 없습니다: $memberId")
        // 10. Entity -> DTO 변환
        return member.toResponse()
    }
}
