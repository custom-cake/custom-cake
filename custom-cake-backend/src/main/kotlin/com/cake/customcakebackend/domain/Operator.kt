package com.cake.customcakebackend.domain

import java.time.LocalDateTime

data class Operator (
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val isAuthenticated: Boolean,
    val lastConnDate: LocalDateTime,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime
)