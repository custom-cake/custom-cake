package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.domain.Store

data class StoreManagementInfoResponse(
    val id: Long,
    val operatorId: Long,
    val businessRegistrationNo: String,
    val representativeName: String,
    val address: String,
    val phone: String? = "",
    val name: String,  // 매장 이름
    val description: String? = "",
    val openTime: Map<DayOfWeekUnit, String>,  // e.g. mapof(MON to "12:00~19:00", THU to "12:00~19:00")
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수
    val thumbnailImageUrl: String,
    val viewCount: Int,
    val ratingSum: Int
)

fun Store.toInfoResponse() = StoreManagementInfoResponse (
    id = this.id,
    operatorId = this.id,
    businessRegistrationNo = this.businessRegistrationNo,
    representativeName = this.representativeName,
    address = "${this.zipCode}, ${this.baseAddress}, ${this.detailAddress}",
    phone = this.phone,
    name = this.name,
    description = this.description,
    openTime = this.openTime,
    reservationPeriod = this.reservationPeriod,
    reservationPerPeriodCount = this.reservationPerPeriodCount,
    thumbnailImageUrl = this.thumbnailImageUrl,
    viewCount = this.viewCount,
    ratingSum = this.ratingSum
)