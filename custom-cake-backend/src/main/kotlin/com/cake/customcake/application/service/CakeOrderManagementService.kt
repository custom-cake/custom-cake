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
    private val optionByCakePort: OptionByCakePort,
    private val cakeOptionPort: CakeOptionPort
): CakeOrderManagementUseCase{
    override fun loadCakeOrderList(storeId: Long, orderStatus: OrderStatus): CakeOrderManagementListResponse {
        val designOrderList = cakeDesignOrderPort.loadListByStoreIdAndOrderStatus(storeId, orderStatus)
        val customOrderList = cakeCustomOrderPort.loadListByStoreIdAndOrderStatus(storeId, orderStatus)

        return CakeOrderManagementListResponse(
            designOrderList = designOrderList.map {
                it.toResponse(
                    userNameAndPhone = userPort.loadUserNameAndPhone(it.userId),
                    cakeItemNameAndImage = cakeItemPort.loadCakeItemNameAndImage(it.cakeItemId),
                    optionByCakeList = optionByCakePort.loadListByIdList(it.optionByCakeIdList)
                )
            },
            customOrderList = customOrderList.map {
                it.toResponse(
                    userNameAndPhone = userPort.loadUserNameAndPhone(it.userId),
                    optionList = cakeOptionPort.loadListByIdList(
                        listOfNotNull(
                            it.cakeCustomOrderSheet.option1Id,
                            it.cakeCustomOrderSheet.option2Id,
                            it.cakeCustomOrderSheet.option3IdList.first().toLong(),  // TODO list
                        )
                    )
                )
            }
        )
    }

    @Transactional
    override fun approveCakeOrder(type: OrderType, orderId: Long) {
        when (type) {
            OrderType.DESIGN -> cakeDesignOrderPort.approveCakeOrder(orderId)
            OrderType.CUSTOM -> cakeCustomOrderPort.approveCakeOrder(orderId)
        }
    }
}