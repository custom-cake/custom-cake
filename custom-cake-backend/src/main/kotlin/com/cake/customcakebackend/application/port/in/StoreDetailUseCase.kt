package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.ReviewListResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreDetailInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreNotificationListResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.StoreNotificationResponse

interface StoreDetailUseCase {

    fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse
    fun storeNotificationList(storeId: Long): StoreNotificationListResponse
    fun storeNotificationDetailInfo(notificationId: Long): StoreNotificationResponse
    fun storeReviewList(storeId: Long): ReviewListResponse
}