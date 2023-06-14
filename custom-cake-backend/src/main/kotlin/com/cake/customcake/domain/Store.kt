package com.cake.customcake.domain

import com.cake.customcake.common.DayOfWeekUnit
import com.cake.customcake.common.TempStoreRegion
import java.time.LocalDateTime

data class Store(
    val id: Long = 0,
    val operatorId: Long,
    val businessRegistrationNo: String,
    val representativeName: String,
    val zipCode: String,
    val baseAddress: String,
    val detailAddress: String? = "",
    val x: String = "",
    val y: String = "",
    val region: TempStoreRegion,
    val phone: String? = "",
    val name: String,  // 매장 이름
    val description: String? = "",
    val openTime: Map<DayOfWeekUnit, String>,  // e.g. mapOf(MON to "12:00~19:00", THU to "12:00~19:00")
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수
    val thumbnailImageUrl: String,
    val viewCount: Int,
    val ratingSum: Int,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)