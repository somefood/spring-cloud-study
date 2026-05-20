package com.ecommerce.member.api

import com.ecommerce.member.domain.Member

data class JoinMemberRequest(
    val email: String,
    val name: String,
    val address: String,
)

data class MemberResponse(
    val id: Long,
    val email: String,
    val name: String,
)

fun JoinMemberRequest.toEntity(): Member {
    return Member(
        email = this.email,
        name = this.name,
        address = this.address
    )
}

fun Member.toResponse(): MemberResponse {
    // Entity의 id는 null일 수 없다고 확신 (DB에서 조회했거나 저장된 후이므로)
    return MemberResponse(
        id = this.id!!,
        email = this.email,
        name = this.name
    )
}
