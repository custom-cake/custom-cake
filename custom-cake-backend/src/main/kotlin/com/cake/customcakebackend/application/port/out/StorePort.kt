package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Store

interface StorePort {
    fun load(operatorId: Long): Store
    fun save(store: Store)
}