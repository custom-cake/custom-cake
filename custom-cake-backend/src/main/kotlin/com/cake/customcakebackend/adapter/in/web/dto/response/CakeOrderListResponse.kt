package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.OrderStatus
import com.cake.customcakebackend.domain.CakeCustomOrder
import com.cake.customcakebackend.domain.CakeDesignOrder

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
    val requirements: String, // cakeItem 정보
    val orderStatus: OrderStatus, // cakeItem 정보
    val paymentAmount: Int, // cakeItem 정보
    val pickupDatetime: String, // cakeItem 정보
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
)

fun CakeCustomOrder.toResponse(cakeItemName: String): CustomOrderResponse = CustomOrderResponse(
    orderId = this.id,
    storeId = this.storeId,
//    cakeItemId = this.cakeItemId,
//    cakeItemName = cakeItemName,
//    optionByCakeList = listOf(""), // this.optionByCakeIdList
//    requirements = this.requirements,
//    orderStatus = this.orderStatus,
//    paymentAmount = this.paymentAmount,
//    pickupDatetime = this.pickupDatetime,
//    purchaseConfirmationDate = this.purchaseConfirmationDate,
)