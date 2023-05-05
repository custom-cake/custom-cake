package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.OperatorLoginRequest
import com.cake.customcake.adapter.`in`.web.dto.response.OperatorLoginResponse


interface OperatorLoginUseCase {
    fun login(operatorLoginRequest: OperatorLoginRequest): OperatorLoginResponse?
}