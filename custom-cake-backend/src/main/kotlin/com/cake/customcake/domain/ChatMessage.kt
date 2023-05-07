package com.cake.customcake.domain

import java.time.LocalDateTime

class ChatMessage (
    val id: Long,
    val chatRoomId: Long,
    val sendEmail: String,
    val recvEmail: String,
    val message: String,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)