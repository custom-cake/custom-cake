package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeOrderListResponse
import com.cake.customcakebackend.domain.CakeDesignOrder

interface DesignCakeOrderUseCase {
    fun order(designCakeOrderRequest: DesignCakeOrderRequest)
    fun orderList(userId: Long): CakeOrderListResponse
}