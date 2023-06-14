package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.request.OperatorLoginRequest
import com.cake.customcake.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcake.adapter.`in`.web.dto.response.toResponse
import com.cake.customcake.application.port.`in`.OperatorLoginUseCase
import com.cake.customcake.application.port.out.OperatorPort
import org.springframework.stereotype.Service

@Service
class OperatorService(
    private val operatorPort: OperatorPort
): OperatorLoginUseCase {
    override fun login(operatorLoginRequest: OperatorLoginRequest): OperatorLoginResponse? {
        val (operator, storeId) = operatorPort.loadByEmailAndPassword(operatorLoginRequest.email, operatorLoginRequest.password)

        return operator
            ?.toResponse(storeId)
    }
}