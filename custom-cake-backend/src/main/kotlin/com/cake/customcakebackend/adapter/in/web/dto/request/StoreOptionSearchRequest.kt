package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.DayOfWeekUnit
import com.cake.customcakebackend.common.TempStoreRegion

data class StoreOptionSearchRequest(
    val days: List<DayOfWeekUnit>,
    val regions: List<TempStoreRegion>
)