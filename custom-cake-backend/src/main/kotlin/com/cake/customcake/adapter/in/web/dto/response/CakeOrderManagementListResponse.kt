package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.OrderStatus
import com.cake.customcake.common.OrderType
import com.cake.customcake.domain.CakeCustomOrder
import com.cake.customcake.domain.CakeDesignOrder

data class CakeOrderManagementListResponse(
    // designOrder
    val designOrderList: List<DesignOrderManagementResponse>,
    // customOrder
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
    val orderType: OrderType = OrderType.CUSTOM,
    val userName: String,
    val userPhone: String,
    val optionInfo: List<String>,
    val customCakeImage: String,  // 케이크 스케치 이미지
    val additionalImageList: List<String> = listOf(),  // 첨부 파일 리스트
    val requirements: String,
    val orderStatus: OrderStatus,
    val paymentAmount: Int,
    val pickupDatetime: String,
)

fun CakeCustomOrder.toResponse(
    userNameAndPhone: Pair<String, String>,
    optionList: List<String>
): CustomOrderManagementResponse = CustomOrderManagementResponse(
    orderId = this.id,
    userName = userNameAndPhone.first,
    userPhone = userNameAndPhone.second,
    optionInfo = optionList,
    customCakeImage = this.cakeCustomOrderSheet.cakeCustomImageUrl,
    additionalImageList = this.cakeCustomOrderSheet.additionalImageList,
    requirements = this.cakeCustomOrderSheet.userRequirements + this.cakeCustomOrderSheet.otherRequirements,
    orderStatus = this.orderStatus,
    paymentAmount = this.paymentAmount,
    pickupDatetime = this.pickupDatetime.toString().replace('-', '/').replace('T', ' '),
)