package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.domain.CakeItem
import com.cake.customcakebackend.domain.Store

data class StoreDetailInfoResponse (
    val id: Long,
    val address: String,
    val phone: String? = "",
    val name: String,  // 매장 이름
    val description: String? = "",
    val openTime: Map<DayOfWeekUnit, String>,
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수
    val thumbnailImageUrl: String,
    val reviewScore: Float,  // 소수점 한자리수

    // 디자인 케이크 상품 리스트
    val cakeItemList: List<CakeItemResponse>
)

fun Store.toResponse(reviewScore: Float, cakeItemList: List<CakeItem>) = StoreDetailInfoResponse (
    id = this.id,
    address = "${this.zipCode}, ${this.baseAddress}, ${this.detailAddress}",
    phone = this.phone,
    name = this.name,
    description = this.description,
    openTime = this.openTime,
    reservationPeriod = this.reservationPeriod,
    reservationPerPeriodCount = this.reservationPerPeriodCount,
    thumbnailImageUrl = this.thumbnailImageUrl,
    reviewScore = reviewScore,
    cakeItemList = cakeItemList.map { it.toResponse() }
)