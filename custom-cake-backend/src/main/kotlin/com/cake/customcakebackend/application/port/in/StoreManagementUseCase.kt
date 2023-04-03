package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.domain.Store

interface StoreManagementUseCase {
    fun storeInfo(operatorId: Long): List<Store>
    fun registerStore(storeRegisterRequest: StoreRegisterRequest)
    fun modifyStoreInfo()
}