package com.cake.customcake.adapter.`in`.web.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.Range
import java.time.LocalDateTime

data class DesignCakeOrderRequest(
    val userId: Long,
    val storeId: Long,
    val cakeItemId: Long,
    val optionByCake1Id: Long,
    val optionByCake2Id: Long,
    // TODO option3은 여러 개 선택 가능
    val optionByCake3Id: Long? = null,
    val requirements: String,
    @Range(min = 0)
    val paymentAmount: Int,
    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    val pickupDatetime: LocalDateTime  // 픽업 날짜
)
