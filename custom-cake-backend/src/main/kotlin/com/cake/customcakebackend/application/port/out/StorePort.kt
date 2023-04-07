package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Store

interface StorePort {
    fun load(operatorId: Long): List<Store>
    fun exist(operatorId: Long): Boolean
    fun save(store: Store): Long
}