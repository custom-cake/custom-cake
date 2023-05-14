package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.request.CustomCakeSheetRequest
import com.cake.customcake.adapter.`in`.web.dto.request.DesignCakeOrderRequest
import com.cake.customcake.adapter.`in`.web.dto.response.ApproveCustomSheetResponse
import com.cake.customcake.adapter.`in`.web.dto.response.CakeOrderListResponse
import com.cake.customcake.adapter.`in`.web.dto.response.CustomOrderOptionListResponse
import com.cake.customcake.adapter.`in`.web.dto.response.toResponse
import com.cake.customcake.application.port.`in`.CustomCakeOrderUseCase
import com.cake.customcake.application.port.`in`.DesignCakeOrderUseCase
import com.cake.customcake.application.port.out.*
import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeCustomOrder
import com.cake.customcake.domain.CakeCustomOrderSheet
import com.cake.customcake.domain.CakeDesignOrder
import com.cake.customcake.domain.CakeOption
import com.cake.customcake.exception.CustomCakeException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CakeOrderService(
    private val cakeDesignOrderPort: CakeDesignOrderPort,
    private val cakeCustomOrderPort: CakeCustomOrderPort,
    private val cakeCustomOrderSheetPort: CakeCustomOrderSheetPort,
    private val cakeItemPort: CakeItemPort,
    private val optionByCakePort: OptionByCakePort,
    private val cakeOptionPort: CakeOptionPort
) : DesignCakeOrderUseCase, CustomCakeOrderUseCase {

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

    override fun loadAllCakeOptionList(storeId: Long): CustomOrderOptionListResponse {
        val cakeOptionList: List<CakeOption> = cakeOptionPort.loadAllCakeOptionList(storeId).values.flatten()

        return CustomOrderOptionListResponse(
            storeId = storeId,
            options = cakeOptionList.map { option -> option.toResponse() }
        )
    }

    @Transactional
    override fun makeCustomCakeSheet(customCakeSheetRequest: CustomCakeSheetRequest) {
        cakeCustomOrderSheetPort.save(
            CakeCustomOrderSheet(
                userId = customCakeSheetRequest.userId,
                storeId = customCakeSheetRequest.storeId,
                cakeCustomImageUrl = customCakeSheetRequest.customCakeImage,
                option1Id = customCakeSheetRequest.option1Id,
                option2Id = customCakeSheetRequest.option2Id,
                option3IdList = listOfNotNull(customCakeSheetRequest.option3Id),
                userRequirements = customCakeSheetRequest.userRequirements,
                operatorRequirements = customCakeSheetRequest.operatorRequirements,
                paymentAmount = customCakeSheetRequest.paymentAmount,  // 주문서 확정 시, 가격 저장
                pickupDatetime = customCakeSheetRequest.pickupDatetime,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            )
        )
    }

    @Transactional
    override fun orderCustomCake(customCakeSheetRequest: CustomCakeOrderRequest) {
        // 주문서 가져오기
        val sheet = cakeCustomOrderSheetPort.load(customCakeSheetRequest.cakeCustomSheetId)

        // 커스텀 주문 생성
        cakeCustomOrderPort.save(
            CakeCustomOrder(
                userId = sheet.userId,
                storeId = sheet.storeId,
                cakeCustomOrderSheet = sheet,
                orderStatus = OrderStatus.NEW,
                paymentAmount = sheet.paymentAmount,
                pickupDatetime = sheet.pickupDatetime,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            )
        )
    }

    override fun checkApproveCustomSheet(storeId: Long, userId: Long): ApproveCustomSheetResponse =
        ApproveCustomSheetResponse(approve = cakeCustomOrderSheetPort.hasSheet(storeId, userId))
}