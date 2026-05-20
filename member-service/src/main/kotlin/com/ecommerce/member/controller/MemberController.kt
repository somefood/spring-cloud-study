package com.ecommerce.member.controller

import com.ecommerce.member.service.MemberService
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
@Validated
@RefreshScope
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/greeting")
    fun getGreeting(): String {
        return "greeting"
    }
}