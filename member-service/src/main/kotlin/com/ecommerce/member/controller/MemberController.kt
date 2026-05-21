package com.ecommerce.member.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
@Validated
@RefreshScope
class MemberController() {

    // 3. config-repo에 있는 값을 주입받음
    @Value("\${member.greeting}")
    private lateinit var greeting: String

    @GetMapping("/greeting")
    fun getGreeting(): String {
        return greeting
    }
}