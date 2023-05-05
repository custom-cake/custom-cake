package com.cake.customcake.application.port.out

import com.cake.customcake.domain.Review

interface ReviewPort {
    fun loadNickNameAndReviewList(storeId: Long): Map<String, Review>
    fun calculateReviewScore(storeId: Long): Float
}