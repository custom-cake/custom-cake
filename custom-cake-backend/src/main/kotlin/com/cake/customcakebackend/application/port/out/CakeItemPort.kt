package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeItem

interface CakeItemPort {

    fun loadInfo(cakeItemId: Long): CakeItem
    fun loadCakeItemName(cakeItemId: Long): String
    fun loadCakeItemNameAndImage(cakeItemId: Long): Pair<String, String>
    fun loadList(storeId: Long): List<CakeItem>
    fun save(): Long
    fun modify(cakeItemId: Long): Long
    fun delete(cakeItemId: Long)

}