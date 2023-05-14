package com.cake.customcake.domain

import com.cake.customcake.common.CakeCustomSketch
import com.cake.customcake.common.OrderStatus
import java.time.LocalDateTime

data class CakeCustomOrderSheet (
    val id: Long = 0,
//    val chatRoomId: Long,
    val userId: Long,
    val storeId: Long,
    val cakeCustomImageUrl: String,  // s3 url
    val option1Id: Long,
    val option2Id: Long,
    // TODO option3은 여러 개 선택 가능
    val option3IdList: List<Long> = listOf(),
    val userRequirements: String,
    val operatorRequirements: String,
    val paymentAmount: Int,
    val pickupDatetime: LocalDateTime,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)