package com.cake.customcake.application.port.out

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeDesignOrder

interface CakeDesignOrderPort {
    fun save(cakeDesignOrder: CakeDesignOrder)
    fun loadListByUserId(userId: Long): List<CakeDesignOrder>
    fun loadListByStoreId(storeId: Long, orderStatus: OrderStatus): List<CakeDesignOrder>
    fun approveCakeOrder(orderId: Long)
}