package com.cake.customcakebackend.adapter.`in`.web.dto.request

import com.cake.customcakebackend.common.TempStoreRegion
import java.time.LocalDate

data class StoreOptionSearchRequest(
    val days: List<LocalDate>,
    val regions: List<TempStoreRegion>
)