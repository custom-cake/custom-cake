package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.StoreNotification

interface StoreNotificationPort {

    fun loadNotificationList(storeId: Long): List<StoreNotification>
    fun loadNotification(notificationId: Long): StoreNotification
}