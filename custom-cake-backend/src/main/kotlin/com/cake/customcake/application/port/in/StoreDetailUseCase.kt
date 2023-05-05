package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.response.*

interface StoreDetailUseCase {

    fun storeDetailInfo(storeId: Long): StoreDetailInfoResponse
    fun storeCakeItemDetailInfo(cakeItemId: Long): OptionByCakeListResponse
    fun storeNotificationList(storeId: Long): StoreNotificationListResponse
    fun storeNotificationDetailInfo(notificationId: Long): StoreNotificationResponse
    fun storeReviewList(storeId: Long): ReviewListResponse
    fun storeGalleryList(storeId: Long): GalleryListResponse
}