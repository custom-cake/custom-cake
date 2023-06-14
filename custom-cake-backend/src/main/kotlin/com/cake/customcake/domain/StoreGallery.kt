package com.cake.customcake.domain

import java.time.LocalDateTime

data class StoreGallery(
    val id: Long = 0,
    val storeId: Long,
    val imageUrlList: List<String>,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)