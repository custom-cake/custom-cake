package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.DayOfWeekUnit
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class StoreRegisterRequest(
    @NotEmpty
    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{5}")
    val businessRegistrationNo: String = "",

    @NotEmpty
    val representativeName: String = "",

    @NotEmpty
    val zipCode: String = "",

    @NotEmpty
    val baseAddress: String = "",

    val detailAddress: String? = "",

    val phone: String? = "",

    @NotEmpty
    val name: String = "",

    val description: String? = "",

    val openTime: List<String> = listOf(),  // Map<DayOfWeekUnit, String> = mapOf()

    val fixedDayOffList: List<DayOfWeekUnit>? = null,

    @NotNull
    val reservationPeriod: Int = 30,  // 예약 주기  e.g.  5,10,15,20,30 ...

    @NotNull
    val reservationPerPeriodCount: Int = 1,  // 예약 주기 별 케이크 예약 건수
//    TODO @NotNull
    val thumbnailImageUrl: String = "",
) {

}