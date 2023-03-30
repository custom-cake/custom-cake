package com.cake.customcakebackend.domain

import java.time.LocalDateTime

data class CakeItemImage(
    val id: Long,
    val cakeItemId: Long,
    val url: String,  // s3 image url
    val isThumbnail: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)