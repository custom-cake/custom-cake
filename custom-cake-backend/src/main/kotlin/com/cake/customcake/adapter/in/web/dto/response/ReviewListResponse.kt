package com.cake.customcake.adapter.`in`.web.dto.response

data class ReviewListResponse(
    val storeId: Long,
    val reviewResponseList: List<ReviewResponse>
)
