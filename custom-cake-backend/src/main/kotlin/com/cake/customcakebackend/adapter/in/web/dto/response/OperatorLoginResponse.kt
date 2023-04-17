package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.domain.Operator

data class OperatorLoginResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val isAuthenticated: Boolean,
    val hasStore: Boolean,
    val lastConnDate: String
)

fun Operator.toResponse(hasStore: Boolean): OperatorLoginResponse = OperatorLoginResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    phone = this.phone,
    isAuthenticated = this.isAuthenticated,
    hasStore = hasStore,
    lastConnDate = this.lastConnDate.toString()
)
