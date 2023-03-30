package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderType
import java.time.LocalDateTime

class Review (
    val id: Long,
    val userId: Long,
    val storeId: Long,
    val orderType: OrderType,
    val orderId: Long,
    val content: String,
    val score: Int,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)