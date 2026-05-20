package com.ecommerce.member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing // 1. JPA Auditing 기능 활성화
@SpringBootApplication
class MemberApplication

fun main(args: Array<String>) {
    runApplication<MemberApplication>(*args)
}
