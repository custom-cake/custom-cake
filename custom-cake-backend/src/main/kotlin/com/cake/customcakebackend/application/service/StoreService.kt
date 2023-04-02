package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.StoreManagementUseCase
import com.cake.customcakebackend.application.port.out.StorePort
import com.cake.customcakebackend.domain.Store
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storePort: StorePort
) : StoreManagementUseCase {
    override fun storeInfo(operatorId: Long): Store {
        return storePort.load(operatorId)
    }

    override fun registerStore() {
        TODO("Not yet implemented")
    }

    override fun modifyStoreInfo() {
        TODO("Not yet implemented")
    }

}