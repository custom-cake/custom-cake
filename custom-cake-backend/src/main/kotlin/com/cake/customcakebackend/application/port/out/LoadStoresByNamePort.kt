package com.cake.customcakebackend.application.port.out

import com.cake.customcakebackend.domain.Store

interface LoadStoresByNamePort {
    fun loadByName(query: String): List<Store>
}