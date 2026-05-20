package com.ecommerce.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "members")
class Member(
    // 1. '생성' 시점에 필요한 핵심 비즈니스 필드
    @Column(nullable = false, unique = true)
    var email: String, // email은 변경 가능성이 있으므로 var

    @Column(nullable = false)
    var name: String, // 이름도 개명 등으로 변경 가능성 있음

    @Column(nullable = false)
    var address: String,
    // (패스워드, 회원 등급 등은 추후 다른 장에서 확장)
) : BaseEntity() { // 2. 공통 속성 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null // 3. id는 DB가 생성하므로 주 생성자에서 제외
}
