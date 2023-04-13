package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.common.OrderType
import com.cake.customcakebackend.domain.Review

data class ReviewResponse(
    val id: Long,
    val userNickName: String,
    val orderInfo: OrderInfo,
    val content: String,
    val score: Int,
    val createdAt: String,
    val modifiedAt: String,
)

data class OrderInfo(
    val orderType: OrderType,
    val cakeOption1: String,
    val cakeOption2: String,
    val cakeOption3: String
)

fun Review.toResponse(
    orderType: OrderType,
    cakeOption1: String,
    cakeOption2: String,
    cakeOption3: String = "",
    userNickName: String
) = ReviewResponse(
    id = this.id,
    userNickName = userNickName,
    orderInfo = OrderInfo(
        orderType, cakeOption1, cakeOption2, cakeOption3
    ),
    content = this.content,
    score = this.score,
    createdAt = this.createdAt.toString(),
    modifiedAt = this.modifiedAt.toString()
)
