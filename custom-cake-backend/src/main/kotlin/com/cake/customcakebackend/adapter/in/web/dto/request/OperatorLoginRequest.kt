package com.cake.customcakebackend.adapter.`in`.web.dto.request

import javax.validation.constraints.NotEmpty

data class OperatorLoginRequest (
    @NotEmpty
    val email: String = "",
    @NotEmpty
    val password: String = ""
)