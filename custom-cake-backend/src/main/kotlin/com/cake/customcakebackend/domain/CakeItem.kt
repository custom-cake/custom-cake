package com.cake.customcakebackend.domain

import com.cake.customcakebackend.common.CakeCategory
import java.time.LocalDateTime

data class CakeItem(
    val id: Long = 0,
    val storeId: Long,
    val name: String,
    val category: CakeCategory,
    val description: String? = "",
    val thumbnailImageUrl: String,
    val isOnsale: Boolean,
    val isDeleted: Boolean,
    val price: Int,
    val viewCount: Int,
    val orderCount: Int,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)