package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.OperatorLoginRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.OperatorLoginResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toResponse
import com.cake.customcakebackend.application.port.`in`.OperatorLoginUseCase
import com.cake.customcakebackend.application.port.out.OperatorPort
import com.cake.customcakebackend.application.port.out.StorePort
import org.springframework.stereotype.Service

@Service
class OperatorService(
    private val operatorPort: OperatorPort,
    private val storePort: StorePort
): OperatorLoginUseCase {
    override fun login(operatorLoginRequest: OperatorLoginRequest): OperatorLoginResponse? {
        val operators = operatorPort.loadByEmailAndPassword(operatorLoginRequest.email, operatorLoginRequest.password)

        return if (operators.isEmpty()) {
            null
        } else {
            val operator = operators.first()
            val hasStore = storePort.exist(operator.id)
            operator.toResponse(hasStore)
        }
    }
}