package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.request.DesignCakeOrderRequest

interface DesignCakeOrderUseCase {
    fun order(designCakeOrderRequest: DesignCakeOrderRequest)
}