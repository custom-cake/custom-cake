package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.domain.Store
import lombok.NoArgsConstructor
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class StoreRegisterRequest(
    @NotNull
    val operatorId: Long = 0,
    @NotNull
    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{5}")
    val businessRegistrationNo: String = "",
    @NotNull
    val representativeName: String = "",
    @NotNull
    val zipCode: String = "",
    @NotNull
    val baseAddress: String = "",
    val detailAddress: String? = "",
    val phone: String? = "",
    @NotNull
    val name: String = "",
    val description: String? = "",
    @NotNull
    val openTime: Map<DayOfWeekUnit, String> = mapOf(),
    val dayoffDayList: List<DayOfWeekUnit>? = null,
    @NotNull
    val reservationPeriod: Int = 0,  // 예약 주기  e.g.  5,10,15,20,30 ...
    @NotNull
    val reservationPerPeriodCount: Int = 0,  // 예약 주기 별 케이크 예약 건수
    @NotNull
    val thumbnailImageUrl: String = "",
) {

}