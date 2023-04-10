package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.application.port.`in`.CakeItemManagementUseCase
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.domain.CakeItem
import org.springframework.stereotype.Service

@Service
class CakeItemService(
    private val cakeItemPort: CakeItemPort
) : CakeItemManagementUseCase {
    override fun loadCakeItemInfo(cakeItemId: Long): CakeItem {
        TODO("Not yet implemented")
    }

    override fun loadCakeItemList(storeId: Long): List<CakeItem> =
        cakeItemPort.loadList(storeId)

    override fun saveCakeItem(): Long {
        TODO("Not yet implemented")
    }

    override fun modifyCakeItem(): Long {
        TODO("Not yet implemented")
    }

    override fun deleteCakeItem() {
        TODO("Not yet implemented")
    }
}