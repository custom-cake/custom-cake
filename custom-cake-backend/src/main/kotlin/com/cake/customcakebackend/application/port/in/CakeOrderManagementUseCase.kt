package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeOrderManagementListResponse
import com.cake.customcakebackend.common.OrderStatus
import com.cake.customcakebackend.common.OrderType

interface CakeOrderManagementUseCase {
    fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus,): CakeOrderManagementListResponse
    fun approveCakeOrder(type: OrderType, orderId: Long)
}