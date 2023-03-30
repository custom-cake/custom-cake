package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class CakeDesignOrder (
    val id: Long,
    val userId: Long,
    val cakeOption1: CakeOption1,
    val cakeOption2: CakeOption2,
    val cakeOption3: CakeOption3? = null,
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val purchaseConfirmationDate: LocalDate,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)