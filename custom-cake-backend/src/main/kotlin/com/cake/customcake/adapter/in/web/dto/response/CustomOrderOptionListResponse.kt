package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.domain.CakeOption

data class CustomOrderOptionListResponse(
    val storeId: Long,
    val options: List<CakeOption.OptionResponse>
)