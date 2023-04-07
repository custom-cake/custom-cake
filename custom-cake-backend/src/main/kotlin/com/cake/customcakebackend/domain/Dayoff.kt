package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.DayoffType
import java.time.LocalDate
import java.time.LocalDateTime

data class Dayoff (
    val id: Long = 0,
    val storeId: Long,
    val dayoffType: DayoffType,
    val dayoffDay: DayOfWeekUnit? = null,
    val dayoffDate: LocalDate?= null,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)