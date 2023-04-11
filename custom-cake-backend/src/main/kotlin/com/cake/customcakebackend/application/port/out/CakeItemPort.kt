package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.CakeItem

interface CakeItemPort {

    fun loadInfo(cakeItemId: Long): CakeItem
    fun loadList(storeId: Long): List<CakeItem>
    fun save(): Long
    fun modify(cakeItemId: Long): Long
    fun delete(cakeItemId: Long)

}