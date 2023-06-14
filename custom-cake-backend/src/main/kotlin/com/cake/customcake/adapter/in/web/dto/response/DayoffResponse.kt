package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.DayOfWeekUnit
import com.cake.customcake.common.DayoffType
import com.cake.customcake.domain.Dayoff

data class DayoffResponse(
    val id: Long,
    val dayoffType: DayoffType,
    val dayoffDay: DayOfWeekUnit? = null,
    val dayoffDate: String?= null,
)

fun Dayoff.toResponse(): DayoffResponse = DayoffResponse(
    id = this.id,
    dayoffType = this.dayoffType,
    dayoffDay = this.dayoffDay,
    dayoffDate = this.dayoffDate.toString()
)