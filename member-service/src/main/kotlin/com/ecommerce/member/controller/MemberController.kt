package com.ecommerce.member.controller

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

    @GetMapping("/greeting")
    fun getGreeting(): String {
        return "greeting"
    }
}