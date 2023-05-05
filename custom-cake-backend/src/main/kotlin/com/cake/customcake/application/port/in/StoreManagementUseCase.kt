package com.cake.customcake.application.port.`in`

import com.cake.customcake.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcake.domain.Store

interface StoreManagementUseCase {
    fun storeInfo(operatorId: Long): List<Store>
    fun hasStore(operatorId: Long): Boolean
    fun validateStore(storeId: Long, operatorId: Long): Boolean
    fun registerStore(operatorId: Long, request: StoreRegisterRequest)
    fun modifyStoreInfo()
}