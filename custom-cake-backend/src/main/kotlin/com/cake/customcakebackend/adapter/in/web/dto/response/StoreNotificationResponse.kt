package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.domain.StoreNotification
import java.time.LocalDateTime

data class StoreNotificationResponse (
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val modifiedAt: String
)

fun StoreNotification.toResponse(): StoreNotificationResponse = StoreNotificationResponse(
    id = this.id,
    title = this.title,
    content = this.content,
    createdAt = this.createdAt.toString(),
    modifiedAt = this.modifiedAt.toString()
)