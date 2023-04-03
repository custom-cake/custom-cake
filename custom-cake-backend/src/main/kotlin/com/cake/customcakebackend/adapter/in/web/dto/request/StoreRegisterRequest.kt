package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.DayOfWeekUnit
import lombok.NoArgsConstructor
import javax.validation.constraints.NotNull

data class StoreRegisterRequest(
    @NotNull
    val operatorId: Long,
    @NotNull
    val businessRegistrationNo: String,
    @NotNull
    val representativeName: String,
    @NotNull
    val zipCode: String,
    @NotNull
    val baseAddress: String,
    val detailAddress: String? = "",
    val phone: String? = "",
    @NotNull
    val name: String,  // 매장 이름
    val description: String? = "",
    @NotNull
    val openTime: Map<DayOfWeekUnit, String>,  // e.g. mapOf(MON to "12:00~19:00", THU to "12:00~19:00")
    @NotNull
    val reservationPeriod: Int,  // 예약 주기  e.g.  5,10,15,20,30 ...
    @NotNull
    val reservationPerPeriodCount: Int,  // 예약 주기 별 케이크 예약 건수
    @NotNull
    val thumbnailImageUrl: String,
) {
    
}