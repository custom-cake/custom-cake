package com.cake.customcakebackend.application.port.`in`

import com.cake.customcakebackend.domain.CakeItem

interface CakeItemManagementUseCase {

    fun loadCakeItemInfo(cakeItemId: Long): CakeItem
    fun loadCakeItemList(storeId: Long): List<CakeItem>
    fun saveCakeItem(): Long
    fun modifyCakeItem(): Long
    fun deleteCakeItem()
}