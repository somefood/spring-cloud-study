package com.ecommerce.member.api

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
