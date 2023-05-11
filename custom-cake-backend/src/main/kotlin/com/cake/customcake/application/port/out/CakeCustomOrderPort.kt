package com.cake.customcake.application.port.out

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeCustomOrder

interface CakeCustomOrderPort {
    fun save(cakeCustomOrder: CakeCustomOrder)
    fun loadListByUserId(userId: Long): List<CakeCustomOrder>
    fun loadListByStoreIdAndOrderStatus(storeId: Long, orderStatus: OrderStatus): List<CakeCustomOrder>
    fun approveCakeOrder(orderId: Long)
}