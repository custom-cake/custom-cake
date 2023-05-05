package com.cake.customcake.domain

import com.cake.customcake.common.SocialType
import java.time.LocalDateTime

data class User(
    val id: Long,
    val name: String,
    val nickname: String,
    val phone: String,
    val socialType: SocialType,
    val socialAccountId: String,
    val isAuthenticated: Boolean,
    val lastConnDatetime: LocalDateTime,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)