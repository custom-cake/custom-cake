package com.cake.customcakebackend.adapter.`in`.web.dto.response

data class StoreNotificationListResponse (
    val storeId: Long,
    val notificationList: List<StoreNotificationResponse>
)
