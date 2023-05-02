package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeOrderManagementListResponse
import com.cake.customcakebackend.common.OrderStatus

interface CakeOrderManagementUseCase {
    fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus,): CakeOrderManagementListResponse
}