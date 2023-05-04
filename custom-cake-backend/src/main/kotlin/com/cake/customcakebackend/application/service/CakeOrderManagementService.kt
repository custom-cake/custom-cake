package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeOrderManagementListResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.toResponse
import com.cake.customcakebackend.application.port.`in`.CakeOrderManagementUseCase
import com.cake.customcakebackend.application.port.out.CakeDesignOrderPort
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.application.port.out.LoadUserPort
import com.cake.customcakebackend.application.port.out.OptionByCakePort
import com.cake.customcakebackend.common.OrderStatus
import com.cake.customcakebackend.common.OrderType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CakeOrderManagementService(
    private val cakeDesignOrderPort: CakeDesignOrderPort,
//    private val cakeCustomOrderPort: CakeCustomOrderPort,
    private val userPort: LoadUserPort,
    private val cakeItemPort: CakeItemPort,
    private val optionByCakePort: OptionByCakePort
): CakeOrderManagementUseCase{
    override fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus): CakeOrderManagementListResponse {
        val designOrderList = cakeDesignOrderPort.loadListByStoreId(storeId, orderStatus)
        // TODO get customOrderList

        return CakeOrderManagementListResponse(
            designOrderList = designOrderList.map {
                it.toResponse(
                    userPort.loadUserNameAndPhone(it.userId),
                    cakeItemPort.loadCakeItemNameAndImage(it.cakeItemId),
                    optionByCakePort.loadListByIdList(it.optionByCakeIdList)
                )
            },
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