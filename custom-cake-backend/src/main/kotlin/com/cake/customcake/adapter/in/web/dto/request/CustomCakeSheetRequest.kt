package com.cake.customcake.adapter.`in`.web.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.Range
import java.time.LocalDateTime

data class CustomCakeSheetRequest(
    val userId: Long,
    val storeId: Long,
    val option1Id: Long,
    val option2Id: Long,
    // TODO option3은 여러 개 선택 가능
    val option3Id: Long? = null,
    val customCakeImage: String,  // 케이크 스케치 이미지
    val additionalImageList: List<String> = listOf(),  // 첨부 파일 리스트
    val userRequirements: String,  // 유저의 주문서 전송 시, 요구사항
    val otherRequirements: String = "",  // 특이사항 (주문서 승인 시, 운영자 측 작성)
    @Range(min = 0)
    val paymentAmount: Int,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val pickupDatetime: LocalDateTime  // 픽업 날짜
)
