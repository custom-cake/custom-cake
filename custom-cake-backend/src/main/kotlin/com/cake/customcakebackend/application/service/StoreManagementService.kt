package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.request.StoreRegisterRequest
import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Service

@Service
class StoreManagementService(
    private val storePort: StorePort
) : StoreManagementUseCase {
    override fun storeInfo(operatorId: Long): List<Store> {
        return storePort.load(operatorId)
    }

    override fun registerStore(storeRegisterRequest: StoreRegisterRequest) {
        TODO("storeRegisterRequest to Store, Dayoff")
    }

    override fun modifyStoreInfo() {
        TODO("Not yet implemented")
    }

}