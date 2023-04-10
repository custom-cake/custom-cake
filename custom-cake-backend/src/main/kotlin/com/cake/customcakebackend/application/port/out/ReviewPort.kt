package com.cake.customcakebackend.application.port.out

interface ReviewPort {
    fun calculateReviewScore(storeId: Long): Float
}