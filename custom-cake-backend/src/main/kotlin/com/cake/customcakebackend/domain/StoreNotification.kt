package com.cake.customcakebackend.domain

import java.time.LocalDateTime

data class StoreNotification (
    val id: Long,
    val storeId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)