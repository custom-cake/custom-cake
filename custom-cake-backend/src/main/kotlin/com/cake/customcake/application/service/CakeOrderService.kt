package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderListResponse
import com.cake.customcake.adapter.`in`.web.dto.response.toResponse
import com.cake.customcake.application.port.`in`.DesignCakeOrderUseCase
import com.cake.customcake.application.port.out.CakeItemPort
import com.cake.customcake.application.port.out.OptionByCakePort
import com.cake.customcake.application.port.out.CakeDesignOrderPort
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeDesignOrder
import com.cake.customcake.exception.CustomCakeException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CakeOrderService(
    private val cakeDesignOrderPort: CakeDesignOrderPort,
    // TODO cakeCustomOrderPort
    private val cakeItemPort: CakeItemPort,
    private val optionByCakePort: OptionByCakePort
) : DesignCakeOrderUseCase {

    @Transactional
    override fun order(designCakeOrderRequest: DesignCakeOrderRequest) {
        val optionByCakeIdList = listOfNotNull(
            designCakeOrderRequest.optionByCake1Id,
            designCakeOrderRequest.optionByCake2Id,
            designCakeOrderRequest.optionByCake3Id
        )

        // 구매 금액 계산, cakeItem, optionByCake 검증
        val cakeItem = cakeItemPort.loadInfo(designCakeOrderRequest.cakeItemId)
        val optionsPrice = optionByCakePort.loadListByIdList(optionByCakeIdList, designCakeOrderRequest.cakeItemId)
            .map { it.price }.toIntArray().sum()

        if (cakeItem.storeId != designCakeOrderRequest.storeId)
            throw CustomCakeException.BadRequestException("주문 실패 : 매장 검증 오류")
        if (designCakeOrderRequest.paymentAmount != (cakeItem.price + optionsPrice))
            throw CustomCakeException.BadRequestException("주문 실패 : 결제 금액 오류")

        val cakeDesignOrderUser = CakeDesignOrder(
            userId = designCakeOrderRequest.userId,
            storeId = designCakeOrderRequest.storeId,
            cakeItemId = designCakeOrderRequest.cakeItemId,
            optionByCakeIdList = optionByCakeIdList,
            requirements = designCakeOrderRequest.requirements,
            orderStatus = OrderStatus.NEW,
            paymentAmount = designCakeOrderRequest.paymentAmount,
            pickupDatetime = designCakeOrderRequest.pickupDatetime,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        cakeDesignOrderPort.save(cakeDesignOrderUser)
    }

    override fun orderList(userId: Long): CakeOrderListResponse {
        val designOrderList = cakeDesignOrderPort.loadListByUserId(userId)
        // TODO get customOrderList

        return CakeOrderListResponse(
            userId = userId,
            designOrderList = designOrderList.map {
                it.toResponse(
                    cakeItemPort.loadCakeItemName(it.cakeItemId),
                    optionByCakePort.loadListByIdList(it.optionByCakeIdList)
                )
            },
            customOrderList = listOf()
        )
    }
}