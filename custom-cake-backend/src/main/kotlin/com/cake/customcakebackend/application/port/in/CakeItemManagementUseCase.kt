package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeItemInfoResponse
import com.cake.customcakebackend.adapter.`in`.web.dto.response.CakeItemResponse

interface CakeItemManagementUseCase {

    fun loadCakeItemInfo(cakeItemId: Long): CakeItemInfoResponse
    fun loadCakeItemList(storeId: Long): List<CakeItemResponse>
    fun saveCakeItem(): Long
    fun modifyCakeItem(): Long
    fun deleteCakeItem(cakeItemId: Long)
}