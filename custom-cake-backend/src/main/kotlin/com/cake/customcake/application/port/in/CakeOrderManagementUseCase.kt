package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderManagementListResponse
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.OrderType

interface CakeOrderManagementUseCase {
    fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus,): CakeOrderManagementListResponse
    fun approveCakeOrder(type: OrderType, orderId: Long)
}