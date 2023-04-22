package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.request.OperatorLoginRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse


interface OperatorLoginUseCase {
    fun login(operatorLoginRequest: OperatorLoginRequest): OperatorLoginResponse?
}