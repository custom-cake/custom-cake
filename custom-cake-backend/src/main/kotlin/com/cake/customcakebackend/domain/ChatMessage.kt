package com.cake.customcakebackend.domain

import java.time.LocalDateTime

class ChatMessage (
    val id: Long,
    val chatRoomId: Long,
    val message: String,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)