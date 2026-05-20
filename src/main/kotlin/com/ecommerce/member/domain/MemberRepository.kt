package com.ecommerce.member.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {

    /**
     * Spring Data JPA 쿼리 메서드
     * 이메일을 기준으로 회원을 조회한다.
     */
    fun findByEmail(email: String): Member?
}
