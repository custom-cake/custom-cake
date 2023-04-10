package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.CakeCategory

data class CakeItemResponse(
    val id: Long,
    val storeId: Long,
    val name: String,
    val description: String? = "",
    val category: CakeCategory,
    val image: String,
    val price: Int,
    val isOnsale: Boolean,
)