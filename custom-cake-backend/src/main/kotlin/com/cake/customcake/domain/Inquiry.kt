package com.cake.customcake.domain

import java.time.LocalDateTime

data class Inquiry (
    val id: Long,
    val userId: Long,
    val storeId: Long,
    val title: String,
    val content: String,
    val isAnswered: Boolean,
    val answer: String? = "",
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)