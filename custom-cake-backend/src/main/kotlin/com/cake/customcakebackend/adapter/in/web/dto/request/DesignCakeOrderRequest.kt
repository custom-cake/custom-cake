package com.cake.customcakebackend.adapter.`in`.web.dto.request

data class DesignCakeOrderRequest(
    val userId: Long,
    val storeId: Long,
    val cakeItemId: Long,
    val optionByCake1Id: Long,
    val optionByCake2Id: Long,
    // TODO option3은 여러 개 선택 가능
    val optionByCake3Id: Long? = null,
    val requirements: String,
    val paymentAmount: Int,
)