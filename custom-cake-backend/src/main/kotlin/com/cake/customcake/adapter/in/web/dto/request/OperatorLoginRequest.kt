package com.cake.customcake.adapter.`in`.web.dto.request

import javax.validation.constraints.NotEmpty

data class OperatorLoginRequest (
    @NotEmpty
    val email: String = "",
    @NotEmpty
    val password: String = ""
)