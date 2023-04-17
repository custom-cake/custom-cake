package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.DayoffType
import com.cake.customcakebackend.domain.Dayoff

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