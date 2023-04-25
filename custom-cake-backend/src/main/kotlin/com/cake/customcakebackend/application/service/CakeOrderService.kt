package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcakebackend.application.port.`in`.DesignCakeOrderUseCase
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.application.port.out.SaveCakeDesignOrderPort
import com.cake.customcakebackend.domain.CakeDesignOrder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CakeOrderService(
    private val saveCakeDesignOrderPort: SaveCakeDesignOrderPort,
    private val cakeItemPort: CakeItemPort,
    private val cakeOptionService: CakeOptionService
) : DesignCakeOrderUseCase {

    // Todo : 코드 리펙토링
    @Transactional
    override fun order(designCakeOrderRequest: DesignCakeOrderRequest) {
        val storeId = cakeItemPort.loadInfo(designCakeOrderRequest.cakeItemId).storeId
        val optionByCakeIdList = listOfNotNull(
            designCakeOrderRequest.cakeOption1,
            designCakeOrderRequest.cakeOption2,
            designCakeOrderRequest.cakeOption3
        ).mapIndexed { idx, option ->
            cakeOptionService.saveCakeOption(
                storeId = storeId,
                cakeOptionType = idx + 1,
                cakeOptionRequest = option
            ).second
        }

        val cakeDesignOrderUser = CakeDesignOrder(
            userId = designCakeOrderRequest.userId,
            cakeItemId = designCakeOrderRequest.cakeItemId,
            optionByCakeIdList = optionByCakeIdList,
            requirements = designCakeOrderRequest.requirements,
            orderStatus = designCakeOrderRequest.orderStatus,
            paymentAmount = designCakeOrderRequest.paymentAmount,
            purchaseConfirmationDate = designCakeOrderRequest.purchaseConfirmationDate,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        saveCakeDesignOrderPort.save(cakeDesignOrderUser)
    }
}