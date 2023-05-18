package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.domain.CakeCustomOrder
import com.cake.customcake.domain.CakeDesignOrder
import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.validator.constraints.Range
import java.time.LocalDateTime

data class CakeOrderListResponse(
    val userId: Long,
    val designOrderList: List<DesignOrderResponse>,
    val customOrderList: List<CustomOrderResponse>
)

data class DesignOrderResponse(
    val orderId: Long,
    val storeId: Long,
    val cakeItemId: Long,
    val cakeItemName: String, // cakeItem 정보
//    val cakeItemImage: String, // cakeItem 정보
    val optionByCakeList: List<String>,
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val pickupDatetime: String,
    val purchaseConfirmationDate: String? = null
)

fun CakeDesignOrder.toResponse(cakeItemName: String, optionByCakeList: List<String>): DesignOrderResponse = DesignOrderResponse(
    orderId = this.id,
    storeId = this.storeId,
    cakeItemId = this.cakeItemId,
    cakeItemName = cakeItemName,
    optionByCakeList = optionByCakeList,
    requirements = this.requirements,
    orderStatus = this.orderStatus,
    paymentAmount = this.paymentAmount,
    pickupDatetime = this.pickupDatetime.toString(),
    purchaseConfirmationDate = this.purchaseConfirmationDate.toString(),
)

data class CustomOrderResponse(
    val orderId: Long,
    val storeId: Long,
    val optionList: List<String>,
    val customCakeImage: String,  // 케이크 스케치 이미지
    val additionalImageList: List<String> = listOf(),  // 첨부 파일 리스트
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val pickupDatetime: String,
    val purchaseConfirmationDate: String? = null
)

fun CakeCustomOrder.toResponse(optionList: List<String>): CustomOrderResponse = CustomOrderResponse(
    orderId = this.id,
    storeId = this.storeId,
    optionList = optionList,
    requirements = this.cakeCustomOrderSheet.userRequirements + this.cakeCustomOrderSheet.otherRequirements,
    customCakeImage = this.cakeCustomOrderSheet.cakeCustomImageUrl,
    additionalImageList = this.cakeCustomOrderSheet.additionalImageList,
    orderStatus = this.orderStatus,
    paymentAmount = this.paymentAmount,
    pickupDatetime = this.pickupDatetime.toString(),
    purchaseConfirmationDate = this.purchaseConfirmationDate.toString(),
)