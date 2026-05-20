package com.ecommerce.member.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass // 1. 이 클래스가 공통 매핑 정보를 포함함을 명시
@EntityListeners(AuditingEntityListener::class) // 2. 엔티티 이벤트를 감지하여 자동 적용
abstract class BaseEntity {

    @CreatedDate // 3. 엔티티 생성 시 자동으로 날짜 주입
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null
        private set // 4. 외부에서 수정 불가능하도록 private set

    @LastModifiedDate // 5. 엔티티 수정 시 자동으로 날짜 주입
    var updatedAt: LocalDateTime? = null
        private set
}
