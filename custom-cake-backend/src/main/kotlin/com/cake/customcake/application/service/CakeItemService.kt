package com.cake.customcake.application.service

import com.cake.customcake.adapter.`in`.web.dto.response.CakeItemInfoResponse
import com.cake.customcake.adapter.`in`.web.dto.response.CakeItemResponse
import com.cake.customcake.adapter.`in`.web.dto.response.toResponse
import com.cake.customcake.application.port.`in`.CakeItemManagementUseCase
import com.cake.customcake.application.port.out.CakeItemPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CakeItemService(
    private val cakeItemPort: CakeItemPort
) : CakeItemManagementUseCase {
    override fun loadCakeItemInfo(cakeItemId: Long): CakeItemInfoResponse {
        TODO("Not yet implemented")
    }

    override fun loadCakeItemList(storeId: Long): List<CakeItemResponse> =
        cakeItemPort.loadList(storeId).map { it.toResponse() }

    @Transactional
    override fun saveCakeItem(): Long {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun modifyCakeItem(): Long {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteCakeItem(cakeItemId: Long) =
        cakeItemPort.delete(cakeItemId)
}