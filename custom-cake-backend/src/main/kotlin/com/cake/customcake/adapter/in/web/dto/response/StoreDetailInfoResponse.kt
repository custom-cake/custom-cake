package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.DayOfWeekUnit
import com.cake.customcake.domain.CakeItem
import com.cake.customcake.domain.Dayoff
import com.cake.customcake.domain.Store
import com.fasterxml.jackson.annotation.JsonProperty

data class StoreDetailInfoResponse (
    val id: Long,
    val address: String,
    val phone: String? = "",
    val name: String,  // 매장 이름
    val description: String? = "",
    @JsonProperty("openTime")
    val openTime: Map<DayOfWeekUnit, String>,
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수
    val thumbnailImageUrl: String,
    val reviewScore: Float,  // 소수점 한자리수

    // 디자인 케이크 상품 리스트
    val cakeItemList: List<CakeItemResponse>,
    val dayoffList: List<DayoffResponse>
)

fun Store.toResponse(reviewScore: Float, cakeItemList: List<CakeItem>, dayoffList: List<Dayoff>) =
    StoreDetailInfoResponse (
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
        cakeItemList = cakeItemList.map { it.toResponse() },
        dayoffList = dayoffList.map { it.toResponse() }
    )