package com.cake.customcake.domain

import com.cake.customcake.common.ChatStatus
import java.time.LocalDateTime

data class ChatRoom (
    val id: Long,
    val userId: Long,
    val operatorId: Long,
    val recentCakeCustomOrderSheetId: Long,
    val chatStatus: ChatStatus,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)