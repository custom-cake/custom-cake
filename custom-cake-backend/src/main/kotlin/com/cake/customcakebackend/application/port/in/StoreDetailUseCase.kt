package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.*

interface StoreDetailUseCase {

    fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse
    fun storeCakeItemDetailInfo(cakeItemId: Long): OptionByCakeListResponse
    fun storeNotificationList(storeId: Long): StoreNotificationListResponse
    fun storeNotificationDetailInfo(notificationId: Long): StoreNotificationResponse
    fun storeReviewList(storeId: Long): ReviewListResponse
}