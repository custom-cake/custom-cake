package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.OrderStatus
import java.time.LocalDate

data class DesignCakeOrderRequest(
    val userId: Long,
    val cakeItemId: Long,
    val cakeOption1: CakeOption1AddRequest,
    val cakeOption2: CakeOption2AddRequest,
    val cakeOption3: CakeOption3AddRequest? = null,
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val purchaseConfirmationDate: LocalDate
)
