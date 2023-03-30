package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderType
import java.time.LocalDateTime

data class PushNotification (
    val id: Long,
    val userId: Long,
    val orderType: OrderType,
    val orderId: Long,
    val message: String,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)