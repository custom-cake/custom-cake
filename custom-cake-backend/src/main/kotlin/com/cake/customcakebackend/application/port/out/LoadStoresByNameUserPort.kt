package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Store

interface LoadStoresByNameUserPort {
    fun loadByName(query: String): List<Store>
}