package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.domain.Operator

data class OperatorLoginResponse(
    val id: Long,
    val name: String,
    val email: String,
    val phone: String,
    val isAuthenticated: Boolean,
    var storeId: Long? = null,
    val lastConnDate: String
)

fun Operator.toResponse(storeId: Long?): OperatorLoginResponse = OperatorLoginResponse(
    id = this.id,
    name = this.name,
    email = this.email,
    phone = this.phone,
    isAuthenticated = this.isAuthenticated,
    storeId = storeId,
    lastConnDate = this.lastConnDate.toString()
)
