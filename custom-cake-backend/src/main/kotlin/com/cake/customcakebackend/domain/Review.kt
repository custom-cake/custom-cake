package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderOptionsInfo
import com.cake.customcakebackend.common.OrderType
import java.time.LocalDateTime

data class Review (
    val id: Long = 0,
    val userId: Long,
    val storeId: Long,
    val orderType: OrderType,
    val orderOptionsInfo: OrderOptionsInfo,
    val orderId: Long,
    val content: String,
    val score: Int,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)