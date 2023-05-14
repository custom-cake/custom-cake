package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderManagementListResponse
import com.cake.customcake.adapter.`in`.web.dto.response.toResponse
import com.cake.customcake.application.port.`in`.CakeOrderManagementUseCase
import com.cake.customcake.application.port.out.*
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.OrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CakeOrderManagementService(
    private val cakeDesignOrderPort: CakeDesignOrderPort,
    private val cakeCustomOrderPort: CakeCustomOrderPort,
    private val userPort: LoadUserPort,
    private val cakeItemPort: CakeItemPort,
    private val optionByCakePort: OptionByCakePort
): CakeOrderManagementUseCase{
    override fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus): CakeOrderManagementListResponse {
        val designOrderList = cakeDesignOrderPort.loadListByStoreIdAndOrderStatus(storeId, orderStatus)
        val customOrderList = cakeCustomOrderPort.loadListByStoreIdAndOrderStatus(storeId, orderStatus)

        return CakeOrderManagementListResponse(
            designOrderList = designOrderList.map {
                it.toResponse(
                    userPort.loadUserNameAndPhone(it.userId),
                    cakeItemPort.loadCakeItemNameAndImage(it.cakeItemId),
                    optionByCakePort.loadListByIdList(it.optionByCakeIdList)
                )
            },
            // TODO customOrderList
            customOrderList = listOf()
        )
    }

    @Transactional
    override fun approveCakeOrder(type: OrderType, orderId: Long) {
        when (type) {
            OrderType.DESIGN -> cakeDesignOrderPort.approveCakeOrder(orderId)
            OrderType.CUSTOM -> {}
        }
    }
}