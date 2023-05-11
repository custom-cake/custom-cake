package com.cake.customcake.adapter.`in`.web.dto.request

import java.time.LocalDateTime

data class CustomCakeSheetRequest(
    val userId: Long,
    val storeId: Long,
    val option1Id: Long,
    val option2Id: Long,
    // TODO option3은 여러 개 선택 가능
    val option3Id: Long? = null,
    val customCakeImage: String = "",  // 케이크 스케치 이미지
    val additionalImageList: List<String> = listOf(),  // 첨부 파일 리스트
    val userRequirements: String,
    val operatorRequirements: String,
    val specialNote: String,
    val paymentAmount: Int,
    val pickupDatetime: LocalDateTime  // 픽업 날짜
)
