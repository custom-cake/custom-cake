package com.cake.customcake.adapter.`in`.web.dto.response

data class StoreNotificationListResponse (
    val storeId: Long,
    val notificationList: List<StoreNotificationResponse>
)
