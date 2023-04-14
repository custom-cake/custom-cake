package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Review

interface ReviewPort {
    fun loadNickNameAndReviewList(storeId: Long): Map<String, Review>
    fun calculateReviewScore(storeId: Long): Float
}