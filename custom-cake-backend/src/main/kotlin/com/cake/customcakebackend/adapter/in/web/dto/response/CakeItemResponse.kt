package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.CakeCategory
import com.cake.customcakebackend.domain.CakeItem

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

fun CakeItem.toResponse(): CakeItemResponse = CakeItemResponse(
    id = this.id,
    storeId = this.storeId,
    name = this.name,
    description = this.description,
    category = this.category,
    image = this.thumbnailImageUrl,
    price = this.price,
    isOnsale = this.isOnsale
)