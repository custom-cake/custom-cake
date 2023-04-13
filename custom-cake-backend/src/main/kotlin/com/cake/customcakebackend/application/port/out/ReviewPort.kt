package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Review

interface ReviewPort {
    fun loadList(storeId: Long): List<Review>
    fun calculateReviewScore(storeId: Long): Float
}