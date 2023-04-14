package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.domain.OptionByCake

data class OptionByCakeListResponse (
    val cakeItemId: Long,
    val options: List<OptionByCakeResponse>
)

data class OptionByCakeResponse(
    val id: Long,
    val type: Int,  // 1, 2, 3
    val value: String,  // ex "원형, 1호, 1단"
    val price: Int,
)

fun OptionByCake.toResponse(): OptionByCakeResponse = OptionByCakeResponse(
    id = this.id,
    type = this.cakeOptionType,
    value = this.cakeOptionValue,
    price = this.price
)
