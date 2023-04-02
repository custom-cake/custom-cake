package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.domain.Store

interface StoreManagementUseCase {
    fun storeInfo(operatorId: Long): Store
    fun registerStore()
    fun modifyStoreInfo()
}