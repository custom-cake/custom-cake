package com.cake.customcake.application.port.out

import com.cake.customcake.domain.Store

interface StorePort {
    fun loadByOperatorId(operatorId: Long): List<Store>
    fun loadByStoreId(storeId: Long): Store
    fun exist(operatorId: Long): Boolean
    fun validateStore(storeId: Long, operatorId: Long): Boolean
    fun save(store: Store): Long
}