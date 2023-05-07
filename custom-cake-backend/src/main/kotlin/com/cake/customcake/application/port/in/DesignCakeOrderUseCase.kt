package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderListResponse

interface DesignCakeOrderUseCase {
    fun order(designCakeOrderRequest: DesignCakeOrderRequest)
    fun orderList(userId: Long): CakeOrderListResponse
}