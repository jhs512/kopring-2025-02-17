package com.ll.domain.member.member.dto

import com.ll.domain.member.member.entity.Member
import io.swagger.v3.oas.annotations.media.Schema

class MemberWithUsernameDto(
    @Schema(hidden = true)
    member: Member
) : MemberDto(member) {
    val username: String

    init {
        this.username = member.username
    }
}

