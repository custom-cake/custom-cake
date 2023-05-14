package com.cake.customcake.domain

import com.cake.customcake.common.OrderStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class CakeCustomOrder (
    val id: Long = 0,
    val userId: Long,
    val storeId: Long,
    val cakeCustomOrderSheet: CakeCustomOrderSheet,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val pickupDatetime: LocalDateTime,
    val purchaseConfirmationDate: LocalDate? = null,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)