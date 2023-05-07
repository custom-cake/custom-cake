package com.cake.customcake.domain

import java.time.LocalDateTime

data class CakeOption3(
    val id: Long = 0,
    val storeId: Long,
    val name: String,
    val price: Int,
    val isUsed: Boolean,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
) : CakeOption()