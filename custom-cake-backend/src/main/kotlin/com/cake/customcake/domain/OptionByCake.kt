package com.cake.customcake.domain

import java.time.LocalDateTime

data class OptionByCake (
    val id: Long,
    val cakeItemId: Long,
    val cakeOptionType: Int,  // 1, 2, 3
    val cakeOptionId: Long,  // CakeOption's id
    val cakeOptionValue: String,
    val price: Int,
    val isUsed: Boolean,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)