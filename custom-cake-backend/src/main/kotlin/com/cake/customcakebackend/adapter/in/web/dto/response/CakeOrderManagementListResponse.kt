package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.OrderStatus
import com.cake.customcakebackend.common.OrderType
import com.cake.customcakebackend.domain.CakeCustomOrder
import com.cake.customcakebackend.domain.CakeDesignOrder

data class CakeOrderManagementListResponse(
    // designOrder
    val designOrderList: List<DesignOrderManagementResponse>,
    // TODO customOrder
    val customOrderList: List<CustomOrderManagementResponse>
)

// pickupDatetime 순 정렬
data class DesignOrderManagementResponse(
    val orderId: Long,
    val orderType: OrderType = OrderType.DESIGN,
    val userName: String,
    val userPhone: String,
    val cakeItemId: Long,
    val cakeItemName: String, 
    val cakeItemImage: String, 
    val optionByCakeInfo: List<String>,
    val requirements: String, 
    val orderStatus: OrderStatus, 
    val paymentAmount: Int, 
    val pickupDatetime: String, 
)

fun CakeDesignOrder.toResponse(
    userNameAndPhone: Pair<String, String>,
    cakeItemNameAndImage:  Pair<String, String>,
    optionByCakeList: List<String>
): DesignOrderManagementResponse = DesignOrderManagementResponse(
    orderId = this.id,
    userName = userNameAndPhone.first,
    userPhone = userNameAndPhone.second,
    cakeItemId = this.cakeItemId,
    cakeItemName = cakeItemNameAndImage.first,
    cakeItemImage = cakeItemNameAndImage.second,
    optionByCakeInfo = optionByCakeList,
    requirements = this.requirements,
    orderStatus = this.orderStatus,
    paymentAmount = this.paymentAmount,
    pickupDatetime = this.pickupDatetime.toString().replace('-', '/').replace('T', ' '),
)

data class CustomOrderManagementResponse(
    val orderId: Long,
)

fun CakeCustomOrder.toResponse(
    cakeItemName: String,
    cakeItemImage: String,
): CustomOrderManagementResponse = CustomOrderManagementResponse(
    orderId = this.id
)