package com.cake.customcake.adapter.`in`.web.dto.response

import com.cake.customcake.common.OrderOptionsInfo
import com.cake.customcake.common.OrderType
import com.cake.customcake.domain.Review

data class ReviewResponse(
    val id: Long,
    val userNickName: String,
    val orderType: OrderType,
    val orderOptionsInfo: OrderOptionsInfo,
    val content: String,
    val score: Int,
    val createdAt: String,
    val modifiedAt: String,
)

fun Review.toResponse(
    userNickName: String
) = ReviewResponse(
    id = this.id,
    userNickName = userNickName,
    orderType = this.orderType,
    orderOptionsInfo = this.orderOptionsInfo,
    content = this.content,
    score = this.score,
    createdAt = this.createdAt.toString(),
    modifiedAt = this.modifiedAt.toString()
)
