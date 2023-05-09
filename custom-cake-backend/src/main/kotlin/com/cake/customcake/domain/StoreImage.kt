package com.cake.customcake.domain

import java.time.LocalDateTime

data class StoreImage(
    val id: Long,
    val storeId: Long,
    val url: String,  // s3 image url
    val isThumbnail: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)