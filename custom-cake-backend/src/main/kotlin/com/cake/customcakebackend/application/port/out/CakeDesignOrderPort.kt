package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.common.OrderStatus
import com.cake.customcakebackend.domain.CakeDesignOrder

interface CakeDesignOrderPort {
    fun save(cakeDesignOrder: CakeDesignOrder)
    fun loadListByUserId(userId: Long): List<CakeDesignOrder>
    fun loadListByStoreId(storeId: Long, orderStatus: OrderStatus): List<CakeDesignOrder>
    fun approveCakeOrder(orderId: Long)
}