package com.cake.customcake.application.port.out

import com.cake.customcake.domain.StoreNotification

interface StoreNotificationPort {

    fun loadNotificationList(storeId: Long): List<StoreNotification>
    fun loadNotification(notificationId: Long): StoreNotification
}