package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class CakeDesignOrder (
    val id: Long = 0,
    val userId: Long,
    val cakeItemId: Long,
    val optionByCakeIdList: List<Long>,
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val purchaseConfirmationDate: LocalDate,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)