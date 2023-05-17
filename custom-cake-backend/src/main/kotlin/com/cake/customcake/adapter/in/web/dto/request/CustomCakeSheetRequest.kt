package com.cake.customcake.adapter.`in`.web.dto.request

import java.time.LocalDateTime

data class CustomCakeSheetRequest(
    val userId: Long = 0,
    val storeId: Long = 0,
    val option1Id: Long = 0,
    val option2Id: Long = 0,
    // TODO option3은 여러 개 선택 가능
    val option3Id: Long? = null,
    val customCakeImage: String = "",  // 케이크 스케치 이미지
    val additionalImageList: List<String> = listOf(),  // 첨부 파일 리스트
    val userRequirements: String = "",  // 유저의 주문서 전송 시, 요구사항
    val otherRequirements: String = "",  // 특이사항 (주문서 승인 시, 운영자 측 작성)
    val paymentAmount: Int = 0,
    val pickupDatetime: LocalDateTime = LocalDateTime.now()  // 픽업 날짜
)
