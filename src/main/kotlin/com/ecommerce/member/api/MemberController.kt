package com.ecommerce.member.api

import com.ecommerce.member.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/join")
    fun join(@RequestBody request: JoinMemberRequest): MemberResponse {
        return memberService.join(request)
    }

    @GetMapping("/{memberId}")
    fun getMember(@PathVariable memberId: Long): MemberResponse {
        return memberService.getMember(memberId)
    }
}
