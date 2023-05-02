package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDate
import java.time.LocalDateTime

data class CakeCustomOrder (
    val id: Long,
    val userId: Long,
    val storeId: Long,
    val customCakeOrderSheetId: Long,
    val paymentAmount: Int,
    val orderStatus: OrderStatus,
    val pickupDatetime: LocalDateTime,
    val purchaseConfirmationDate: LocalDate? = null,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)