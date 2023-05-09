package com.cake.customcake.adapter.`in`.web.dto.request

import com.cake.customcake.common.TempStoreRegion
import java.time.LocalDate

data class StoreOptionSearchRequest(
    val days: List<LocalDate>,
    val regions: List<TempStoreRegion>
)