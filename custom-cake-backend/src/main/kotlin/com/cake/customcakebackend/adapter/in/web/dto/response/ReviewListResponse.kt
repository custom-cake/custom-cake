package com.cake.customcakebackend.adapter.`in`.web.dto.response

import com.cake.customcakebackend.domain.Review

data class ReviewListResponse(
    val storeId: Long,
    val reviewResponseList: List<ReviewResponse>
)
