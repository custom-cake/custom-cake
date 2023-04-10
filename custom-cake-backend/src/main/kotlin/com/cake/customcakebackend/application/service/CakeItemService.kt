package com.cake.customcakebackend.application.service

import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeItemInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeItemResponse
import com.cake.customcakebackend.application.port.`in`.CakeItemManagementUseCase
import com.cake.customcakebackend.application.port.out.CakeItemPort
import com.cake.customcakebackend.domain.CakeItem
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
        cakeItemPort.loadList(storeId).map {
            CakeItemResponse(
                id = it.id,
                storeId = it.storeId,
                name = it.name,
                description = it.description,
                category = it.category,
                image = it.thumbnailImageUrl,
                price = it.price,
                isOnsale = it.isOnsale
            )
        }

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