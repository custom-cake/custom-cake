package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.StoreNotification

interface StoreNotificationPort {

    fun loadNotificationIdList(storeId: Long): List<Long>
    fun loadNotification(notificationId: Long): StoreNotification
}