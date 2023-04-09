package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.DayOfWeekUnit

data class StoreOptionSearchRequest(
    val days: List<DayOfWeekUnit>
)